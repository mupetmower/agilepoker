package agilepoker.domain;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;

//@Entity
public class VoteHistory {
	
	//@OneToOne with GameSession
	private GameSession gameSession;
	
	private Map<String, String> taskAverages = new HashMap<String, String>();
	
	

	public GameSession getGameSession() {
		return gameSession;
	}
	public void setGameSession(GameSession gameSession) {
		this.gameSession = gameSession;
	}
	
	public Map<String, String> getTaskAverages() {
		return taskAverages;
	}
	public void setTaskAverages(Map<String, String> taskAverages) {
		this.taskAverages = taskAverages;
	}
	
}
