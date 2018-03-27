package agilepoker.messages;

public class TaskMessage {
	
	private int gameSessionId;
	private String taskDescription;
	
	public void setGameSessionId(int gameSessionid) {
		this.gameSessionId = gameSessionid;
	}
	
	public int getGameSessionId() {
		return gameSessionId;
	}
	
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	
	public String getTaskDescription() {
		return taskDescription;
	}
	
}
