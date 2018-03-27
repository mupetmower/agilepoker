package agilepoker.messages;



public class TimeMessage {
	private String timePassed;
	private int gameSessionId;
	
	public void setTimePassed(String timePassed) {
		this.timePassed = timePassed;
	}
	public String getTimePassed() {
		return timePassed;
	}
	
	public void setGameSessionId(int gameSessionid) {
		this.gameSessionId = gameSessionid;
	}
	
	public int getGameSessionId() {
		return gameSessionId;
	}
}
