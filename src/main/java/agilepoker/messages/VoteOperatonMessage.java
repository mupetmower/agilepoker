package agilepoker.messages;

public class VoteOperatonMessage {

	private int gameSessionId;
	private boolean showVotes;
	
	public void setGameSessionId(int gameSessionId) {
		this.gameSessionId = gameSessionId;
	}
	
	public int getGameSessionId() {
		return gameSessionId;
	}
	
	public void setShowVotes(boolean showVotes) {
		this.showVotes = showVotes;
	}
	
	public boolean getShowVotes() {
		return showVotes;
	}
	
}
