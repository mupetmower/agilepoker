package agilepoker.controllers;

import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import agilepoker.domain.GameSession;
import agilepoker.domain.User;
import agilepoker.domain.UserStatistics;
import agilepoker.messages.PointsReply;
import agilepoker.messages.PointsUpdateRequest;
import agilepoker.services.GameSessionService;
import agilepoker.services.UserService;
import agilepoker.services.UserStatisticsService;

@Controller
public class PointsMessageController {
	
	GameSessionService gameSessionService;
	UserService userService;
	UserStatisticsService userStatisticsService;
	
	private Logger log = Logger.getLogger(PointsMessageController.class);
	
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
	
	
	
	

//    @MessageMapping("/msg.sendMessage")
//    @SendTo("/topic/pointsupdate")
//    public PointsMessage sendMessage(@Payload PointsMessage pointsMessage) {
//        return pointsMessage;
//    }
//
//    @MessageMapping("/msg.addUserToMessage")
//    @SendTo("/topic/pointsupdate")
//    public PointsMessage addUserToMessage(@Payload PointsMessage PointsMessage, 
//                               SimpMessageHeaderAccessor headerAccessor) {
//        // Add username to web socket session
//        headerAccessor.getSessionAttributes().put("userID", PointsMessage.getSender());
//        return PointsMessage;
//    }
//
//    @MessageMapping("/msg.addPointsToMessage")
//    @SendTo("/topic/pointsupdate")
//    public PointsMessage addPointsToMessage(@Payload PointsMessage PointsMessage, 
//                               SimpMessageHeaderAccessor headerAccessor) {
//        // Add userPoints to web socket session
//        headerAccessor.getSessionAttributes().put("userPoints", PointsMessage.getPoints());
//        return PointsMessage;
//    }
	
	@Transactional
	@MessageMapping("/updateallpoints/{id}")
	@SendTo("/topic/updatepoints/{id}")
	public @ResponseBody PointsReply replyToPointsMessage(@Valid @RequestBody PointsUpdateRequest request, @DestinationVariable int id) throws Exception {
		PointsReply response = new PointsReply();
		
		User user;
		GameSession gameSession;
		String points = "-1";
		
		user = userService.getUserById((int)request.getUserId());
		points = request.getPoints();
		user.setPoints(points);
		userService.saveUser(user);	
		
		
		String task = "";
				
		gameSession = gameSessionService.getSessionById(request.getGameSessionId());
		gameSession.addUser(user);
		gameSession.setShowVotes(request.getShowVotes());
		task = gameSession.getTaskDescription();
		gameSessionService.saveSession(gameSession);
		
		
		Set<User> users = gameSession.getUsers();
		
		users.forEach(u -> response.add(u.getUsername(), u.getPoints()));
		
		//log.info(userStatisticsService.getUserStatisticsById(user.getUserStatistics().getId()));
		//log.info(user.getUserStatistics());
		
		UserStatistics userStats = userStatisticsService.getUserStatisticsById(user.getUserStatistics().getId());
		userStats.setCurrentVote(points.toString());
		if (!points.equals("-1")) {
			userStats.setCurrentVote(points.toString());
		}
		userStatisticsService.saveUserStatistics(userStats);
		
		response.setShowVotes(request.getShowVotes());
		response.setFromCreator(request.isFromCreator());
		
		return response;
	}
	
	
}
