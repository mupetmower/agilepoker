package agilepoker.messages;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import agilepoker.domain.User;
import agilepoker.messages.PointsMessage.MessageType;

public class UserMessage {
	private MessageType type;
    private String content;
    private String sender;
    
    private Integer userId;
    private boolean isInSession;
    
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
    
    
    
    public void setUserId(Integer userId) {
    	this.userId = userId;
    }
    
    public Integer getUserId() {
    	return userId;
    }
    
    public void setIsInSession(boolean isInSession) {
    	this.isInSession = isInSession;
    }
        
    public boolean getIsInSession() {
    	return isInSession;
    }
   
    
}
