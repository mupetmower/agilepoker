package agilepoker.controllers;


import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import agilepoker.domain.GameSession;
import agilepoker.domain.Role;
import agilepoker.domain.User;
import agilepoker.messages.UserMessage;
import agilepoker.services.GameSessionService;
import agilepoker.services.UserService;
import agilepoker.services.UserStatisticsService;

@Controller
public class UserController {

	GameSessionService gameSessionService;
	UserService userService;
	UserStatisticsService userStatisticsService;
	
	private Logger log = Logger.getLogger(UserController.class);
	
	@Autowired
	public void setSessionService(GameSessionService gameSessionService) {
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
	
	@RequestMapping("user/new")
	public void newUser(Model model){
		
		model.addAttribute("user", new User());
	}
	 
	@RequestMapping(path = "user", method = RequestMethod.POST)
	public String saveUser(User user){
		userService.saveUser(user);
		return null;	
	}

	
	@MessageMapping(value = "/kickuser/{gamesessionid}")
	@SendTo("/topic/userkicked/{gamesessionid}")
	public @ResponseBody UserMessage kickUser(@Valid @RequestBody UserMessage request, @DestinationVariable Integer gamesessionid) {
				
		GameSession gs = gameSessionService.getSessionById(gamesessionid);
		
		if (userService.getUserById(request.getUserId()).getRole() != Role.Creator) {
			gs.removeUser(userService.getUserById(request.getUserId()));
		
				
			userService.deleteUser(request.getUserId());
			gameSessionService.saveSession(gs);
			
		}
				
		
		return request;
	}
	
	
	@MessageMapping(value = "/checkinsession/{gamesessionid}")
	@SendTo("/topic/checkinsession/{gamesessionid}")
	public @ResponseBody UserMessage checkInSession(@Valid @RequestBody UserMessage request, @DestinationVariable Integer gamesessionid) {
		UserMessage response = new UserMessage();
		User user = userService.getUserById(request.getUserId());
		
		user.setIsInSession(request.getIsInSession());
		userService.saveUser(user);
		
		response.setUserId(request.getUserId());
		response.setIsInSession(request.getIsInSession());
		
		return response;
	}
	
	
	
	
	
//	@ModelAttribute("user")
//	public User populateNewUser() {
//		User user = new User();
//		userService.saveUser(user);
//		return user;
//	}
	
}
