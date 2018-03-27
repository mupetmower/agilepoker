package agilepoker.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import agilepoker.bootstrap.ProductLoader;
import agilepoker.services.GameSessionService;
import agilepoker.services.UserService;
import agilepoker.services.UserStatisticsService;

@Controller
public class GameSessionStatisticsController {

	GameSessionService gameSessionService;
	UserService userService;
	UserStatisticsService userStatisticsService;
	
	private Logger log = Logger.getLogger(ProductLoader.class);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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
	
	
}
