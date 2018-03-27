package agilepoker.domain;

import java.util.List;

public class GameSessionOptions {
	private int gameSessionId;
	private List<String> pointLabels;
	private List<Object> pointValues;
	
	
	//Possibly add showVotes.. could add in taskdescription and timePassed, but 
	//then might as well also add users, etc.. This is Options.
	
	public void setGameSessionId(int gameSessionId) {
		this.gameSessionId = gameSessionId;
	}
	
	public int getGameSessionId() {
		return gameSessionId;
	}
	
	public void setPointLabels(List<String> pointLabels) {
		this.pointLabels = pointLabels;
	}
	
	public List<String> getPointLabels() {
		return pointLabels;
	}
	
	public void setPointValues(List<Object> pointValues) {
		this.pointValues = pointValues;
	}
	
	public List<Object> getPointValues() {
		return pointValues;
	}

}
