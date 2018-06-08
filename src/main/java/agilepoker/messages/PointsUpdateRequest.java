package agilepoker.messages;

import agilepoker.domain.User;

public class PointsUpdateRequest {
	
	private MessageType type;
    private String content;
    private String sender;
    private String points;
    private int userId;
    private int gameSessionId;
    private boolean showVotes;
    private boolean fromCreator = false;
    

    public enum MessageType {
        POINTS,
        JOIN,
        LEAVE
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
    
    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public int getGameSessionId() {
        return gameSessionId;
    }

    public void setGameSessionId(int gameSessionId) {
        this.gameSessionId = gameSessionId;
    }
    
    public void setShowVotes(boolean showVotes) {
		this.showVotes = showVotes;
	}
	
	public boolean getShowVotes() {
		return showVotes;
	}

	public boolean isFromCreator() {
		return fromCreator;
	}

	public void setFromCreator(boolean fromCreator) {
		this.fromCreator = fromCreator;
	}
    
}
