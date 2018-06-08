package agilepoker.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import agilepoker.domain.GameSession;
import agilepoker.domain.Role;
import agilepoker.domain.User;
import agilepoker.domain.UserStatistics;
import agilepoker.messages.BooleanMessage;
import agilepoker.messages.KickListMessage;
import agilepoker.messages.PointsReply;
import agilepoker.messages.TaskMessage;
import agilepoker.messages.TimeMessage;
import agilepoker.messages.UserMessage;
import agilepoker.messages.UserStatisticsListMessage;
import agilepoker.messages.UserStatisticsMessage;
import agilepoker.messages.VoteOperatonMessage;
import agilepoker.services.GameSessionService;
import agilepoker.services.UserService;
import agilepoker.services.UserStatisticsService;

@Controller
public class GameSessionController {
	GameSessionService gameSessionService;
	UserService userService;
	UserStatisticsService userStatisticsService;
	
	private Logger log = Logger.getLogger(GameSessionController.class);
	
	@Autowired
	public void setgameSessionService(GameSessionService gameSessionService) {
		this.gameSessionService = gameSessionService;
	}
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	public void setUserStatisticsService(UserStatisticsService userStatisticsService) {
		this.userStatisticsService = userStatisticsService;
	}
	
	
	@RequestMapping("gamesession/new")
	public String newGameSession(Model model){
		model.addAttribute("gamesession", new GameSession());
		return "gamesessionform";
	}
	 
	@RequestMapping(value = "gamesession", method = RequestMethod.POST)
	public RedirectView saveGameSession(GameSession gameSession, @RequestParam(value = "txtUsername") String username, 
			String fromInternal, Model model, RedirectAttributes attributes) {
		
		UserStatistics userStats = new UserStatistics();
		//userStats.setCurrentVote("-1");
		userStatisticsService.saveUserStatistics(userStats);
		
		User newUser = new User();
		
		newUser.setUsername(username);
		newUser.setRole(Role.Creator);
		newUser.setIsInSession(true);
		newUser.setUserStatistics(userStats);
		
		userService.saveUser(newUser);
		
		attributes.addFlashAttribute("passedUser", newUser);
		
		gameSession.addUser(newUser);
		gameSession.setPointTypeIsSize(false);
		gameSessionService.saveSession(gameSession);
		
		
		//gameSession = gameSessionService.getSessionById(gameSession.getId());
		
		int i = 1;
		for (User u : gameSession.getUsers()) {
			log.info("User " + i + ": " + u.getUsername());
			i++;
		}
		
		log.info("Username Submitted: " + username);
		
		attributes.addFlashAttribute("gamesession", gameSession);
		attributes.addFlashAttribute("fromInternal", "true");
		
		log.info("fromInternal=" + fromInternal);
		
		RedirectView view = new RedirectView("/gamesession/" + gameSession.getId());
		
		return view;
		
		
	}
	
	@RequestMapping(value = "gamesession/{id}")
    public String showGameSession(@PathVariable Integer id, Model model, RedirectAttributes attributes){
		
        attributes.addFlashAttribute("gamesession", gameSessionService.getSessionById(id));
        //attributes.addFlashAttribute("currentUsers", gameSessionService.getSessionById(id).getCurrentUsers());
                
        //log.info("fromInternal=" + fromInternal);
//        if (fromInternal.equals("true")) {
//        	return "/gamesessionshow";
//        }
        if (!model.asMap().isEmpty() && model.asMap().get("fromInternal").equals("true")) {
        	return "/gamesessionshow";
        }
        
        attributes.addFlashAttribute("gameIdToJoin", id);
		
		return "redirect:/gamesession/join";
    }
	

	@RequestMapping("/gamesession/join")
	public String gameSessionJoin(@RequestParam(value = "gameIdToJoin", required = false) Integer gameId, Model model, RedirectAttributes attributes) {
		if (gameId != null) {
			attributes.addFlashAttribute("gameIdToJoin", gameId);
			
		}
		
		
		return "/gamesessionjoin";
	}
	
	@RequestMapping(value = "joingamesession")
    public RedirectView joinGameSession(@RequestParam Integer id, @RequestParam(value = "txtUsername") String username, Model model, 
    		RedirectAttributes attributes, @RequestParam(value = "fromInternal", required = false, defaultValue = "false") String fromInternal){
		
		GameSession gameSession = gameSessionService.getSessionById(id);		
		
		UserStatistics userStats = new UserStatistics();
		userStatisticsService.saveUserStatistics(userStats);		
		
		User newUser = new User();
		//newUser.setGameSession(gameSession);
		newUser.setUsername(username);
		newUser.setIsInSession(true);
		newUser.setRole(Role.User);
		newUser.setUserStatistics(userStats);
		
		userService.saveUser(newUser);
		
		attributes.addFlashAttribute("passedUser", newUser);
		
		gameSession.addUser(newUser);
		
		log.info("Username Submitted: " + username);
		
		
		int i = 1;
		for (User u : gameSession.getUsers()) {
			log.info("User " + i + ": " + u.getUsername());
			i++;
		}
		
		gameSessionService.saveSession(gameSession);
		
		
		attributes.addFlashAttribute("gamesession", gameSession);
		//attributes.addFlashAttribute("currentUsers", gameSession.getCurrentUsers());
		
		log.info("fromInternal=" + fromInternal);
		if (fromInternal.equals("true")) {
			attributes.addFlashAttribute("fromInternal", "true");
			
		}
		
		return new RedirectView("/gamesession/" + gameSession.getId());
		
    }
	
	
	@Transactional
	@MessageMapping("/updatetask/{id}")
	@SendTo("/topic/updatetask/{id}")
	public @ResponseBody TaskMessage replyToPointsMessage(@Valid @RequestBody TaskMessage request, @DestinationVariable int id) throws Exception {
		
		String task = request.getTaskDescription();
		
		GameSession gameSession;		
		
		TaskMessage response = new TaskMessage();
		response.setTaskDescription(task);
		
		
		gameSession = gameSessionService.getSessionById(id);
		gameSession.setTaskDescription(task);
		
		Set<User> users = gameSession.getUsers();
		gameSessionService.saveSession(gameSession);	
		
		
		users.forEach(user -> {
			UserStatistics userStats = userStatisticsService.getUserStatisticsById(user.getUserStatistics().getId());
			//userStats.setCurrentVote(points.toString());
			String points = userStats.getCurrentVote();
			if (!points.equals("-1")) {
				System.out.println("Calling updateAveragePoints() for " + user.getUsername() + "... Points: " + points);
				userStats.updateAveragePoints(task, points);
				userStatisticsService.saveUserStatistics(userStats);
			}
			
		});	
		
		return request;
	}
	
	
	@MessageMapping(value = "/showvotes/{id}")
	@SendTo("/topic/updatepoints/{id}")
    public @ResponseBody PointsReply showVotes(@Valid @RequestBody VoteOperatonMessage request, @DestinationVariable int id) {
		PointsReply response = new PointsReply();
		GameSession gameSession;
		
		gameSession = gameSessionService.getSessionById(request.getGameSessionId());
		
		gameSession.setShowVotes(request.getShowVotes());		
		gameSessionService.saveSession(gameSession);
		
		Set<User> users = gameSession.getUsers();
		
		users.stream()
				.forEach(u -> response.add(u.getUsername(), u.getPoints()));
		
		response.setShowVotes(request.getShowVotes());
		
		return response;
	}
	
	@MessageMapping(value = "/hidevotes/{id}")
	@SendTo("/topic/updatepoints/{id}")
    public @ResponseBody PointsReply hideVotes(@Valid @RequestBody VoteOperatonMessage request, @DestinationVariable int id) {
		PointsReply response = new PointsReply();
		GameSession gameSession;
		
		gameSession = gameSessionService.getSessionById(request.getGameSessionId());
		
		gameSession.setShowVotes(request.getShowVotes());		
		gameSessionService.saveSession(gameSession);
		
		Set<User> users = gameSession.getUsers();
		
		users.stream()
				.forEach(u -> response.add(u.getUsername(), u.getPoints()));
		
		response.setShowVotes(request.getShowVotes());
		
		return response;
	}
	
	@Transactional
	@MessageMapping(value = "/clearvotes/{id}")
	@SendTo("/topic/clearvotes/{id}")
    public @ResponseBody PointsReply clearVotes(@Valid @RequestBody VoteOperatonMessage request, @DestinationVariable int id) {
		GameSession gameSession;		
		gameSession = gameSessionService.getSessionById(request.getGameSessionId());		
		gameSession.clearAllVotes();
		gameSession.setShowVotes(request.getShowVotes());
		gameSessionService.saveSession(gameSession);
		
		Set<User> users = gameSession.getUsers();
		PointsReply response = new PointsReply();
		users.forEach(u ->  {
			u.setPoints("-1");
			userService.saveUser(u);
			response.add(u.getUsername(), u.getPoints());
		});
		
		response.setShowVotes(request.getShowVotes());
		return response;
	}
	
	@Transactional
	@MessageMapping(value = "/updatevotes/{id}")
	@SendTo("/topic/updatepoints/{id}")
    public @ResponseBody PointsReply updateVotes(@Valid @RequestBody VoteOperatonMessage request, @DestinationVariable int id) {
		PointsReply response = new PointsReply();
		GameSession gameSession;
		
		gameSession = gameSessionService.getSessionById(request.getGameSessionId());
		
		gameSession.setShowVotes(request.getShowVotes());		
		gameSessionService.saveSession(gameSession);
		
		Set<User> users = gameSession.getUsers();
		
		users.forEach(u -> response.add(u.getUsername(), u.getPoints()));
		
		response.setShowVotes(request.getShowVotes());
		
		return response;
	}
	
	
	@MessageMapping(value = "/updatetime/{id}")
	@SendTo("/topic/updatetime/{id}")
	public @ResponseBody TimeMessage updateTime(@Valid @RequestBody TimeMessage request, @DestinationVariable int id) {
		String timePassed = request.getTimePassed();
		
		GameSession gameSession = gameSessionService.getSessionById(id);
		
		gameSession.setTimePassed(timePassed);
		gameSessionService.saveSession(gameSession);
		
		TimeMessage response = new TimeMessage();
		response.setTimePassed(timePassed);
		
		return response;
	}
	
	@MessageMapping(value = "/updateuserlist/{id}")
	@SendTo("/topic/updateuserlist/{id}")
	public @ResponseBody UserStatisticsListMessage updateUserList(@Valid @RequestBody UserMessage request, @DestinationVariable int id) {
		UserStatisticsListMessage response = new UserStatisticsListMessage();

		GameSession gameSession = gameSessionService.getSessionById(id);
		
		//log.info(gameSession.getUsers().toString() + "   " + gameSession.getUsers().size());
		
//		Set<User> users = gameSession.getUsers();
//		
//		for (User u : users) {
//			//log.info(u.getUserStatistics().getCurrentVote());
//			//UserStatistics us = userStatisticsService.getUserStatisticsById(u.getUserStatistics().getId());
//			
//			
//		}
		
		gameSession.getUsers().forEach(u -> {
			UserStatistics us = u.getUserStatistics();
			UserStatisticsMessage usm = new UserStatisticsMessage();
			
			usm.setUserId(u.getId());
			usm.setUsername(u.getUsername());
			
			usm.setAveragePoints(us.getAveragePoints());
			usm.setAverageTime(us.getAverageTime());
			usm.setCurrentVote(us.getCurrentVote());
			
			usm.setPointsPerTask(us.getTaskWithPoints());
			
			us.getTaskWithPoints().forEach((t, p) -> {
//				usm.addPoints(p);
				usm.addTask(t);
			});
				
			List<String> points = new ArrayList<String>();
			for (String t : usm.getPastTasks()) {
				points.add(usm.getPointsPerTask().get(t));
			}
			//usm.setPastVotes(points);
			
			response.addUserStatisticsMessage(usm);
		});
		
		return response;
	}
	
	
	@MessageMapping(value = "/updatepastvotestable/{id}")
	@SendTo("/topic/updatepastvotestable/{id}")
	public @ResponseBody UserStatisticsListMessage updatePastVotesTable(@Valid @RequestBody UserMessage request, @DestinationVariable int id) {
		UserStatisticsListMessage response = new UserStatisticsListMessage();

		GameSession gameSession = gameSessionService.getSessionById(id);
		
		log.info(gameSession.getUsers().toString() + "   " + gameSession.getUsers().size());
		
//		Set<User> users = gameSession.getUsers();
//		
//		for (User u : users) {
//			//log.info(u.getUserStatistics().getCurrentVote());
//			//UserStatistics us = userStatisticsService.getUserStatisticsById(u.getUserStatistics().getId());
//			
//			
//		}
		
		gameSession.getUsers().forEach(u -> {
			UserStatistics us = u.getUserStatistics();
			UserStatisticsMessage usm = new UserStatisticsMessage();
			
			usm.setUserId(u.getId());
			usm.setUsername(u.getUsername());
			
			usm.setPastTasks(us.getPastTasks());
			//usm.setPastVotes((List<String>)us.getPoints());
			
			response.addUserStatisticsMessage(usm);
		});
		
		return response;
	}
	
	
	@MessageMapping(value = "/checkinsession/allusers/{id}")
	@SendTo("/topic/checkinsession/allusers/{id}")
	public @ResponseBody KickListMessage checkInSessionAllUsers(@Valid @RequestBody UserMessage request, @DestinationVariable int id) {
		KickListMessage response = new KickListMessage();
		GameSession gameSession = gameSessionService.getSessionById(id);
		
		gameSession.getUsers().stream()
							.filter(user -> (user.getIsInsession() != true))
							.forEach(user -> response.addUserIdToKickList(user.getId()));
		
		return response;
	}
	
	
	@MessageMapping(value = "/updatepointtype/{id}")
	@SendTo(value = "/topic/updatepointtype/{id}")
	public @ResponseBody BooleanMessage updatePointType(@Valid @RequestBody BooleanMessage request, @DestinationVariable int id) {		
		GameSession gameSession = gameSessionService.getSessionById(id);
		log.info("Updating Point Type: " + request.getBooleanValue());
		gameSession.setPointTypeIsSize(request.getBooleanValue());
		gameSessionService.saveSession(gameSession);
		
		return request;
	}
	
	@MessageMapping(value = "/requestpointtype/{id}")
	@SendTo(value = "/topic/updatepointtype/{id}")
	public @ResponseBody BooleanMessage requestPointType(@Valid @RequestBody BooleanMessage request, @DestinationVariable int id) {		
		GameSession gameSession = gameSessionService.getSessionById(id);		
		BooleanMessage response = new BooleanMessage(gameSession.getPointTypeIsSize());
		return response;
	}
}
