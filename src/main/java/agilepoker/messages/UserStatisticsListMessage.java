package agilepoker.messages;

import java.util.ArrayList;
import java.util.List;

public class UserStatisticsListMessage {

	private List<UserStatisticsMessage> userStatisticsMessages = new ArrayList<UserStatisticsMessage>();
	
	public void addUserStatisticsMessage(UserStatisticsMessage userStatisticsMessage) {
		userStatisticsMessages.add(userStatisticsMessage);
	}
	
	public void setUserStatisticsMessages(List<UserStatisticsMessage> userStatisticsMessages) {
		this.userStatisticsMessages = userStatisticsMessages;
	}
	
	public List<UserStatisticsMessage> getUserStatisticsMessages() {
		return userStatisticsMessages;
	}
}
