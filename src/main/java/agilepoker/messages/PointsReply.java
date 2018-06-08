package agilepoker.messages;

import java.util.ArrayList;
import java.util.List;

public class PointsReply {

	private List<String> usernames = new ArrayList<String>();
	private List<String> points = new ArrayList<String>();
	
	private boolean showVotes = false;
	private boolean fromCreator = false;
	
	
	public void setUsernames(List<String> usernames) {
		this.usernames = usernames;
	}
	
	public List<String> getUsernames() {
		return usernames;
	}
	
	public void setPoints(List<String> points) {
		this.points = points;
	}
	
	public List<String> getPoints() {
		return points;
	}
	
	public void setShowVotes(boolean showVotes) {
		this.showVotes = showVotes;
	}
	
	public boolean getShowVotes() {
		return showVotes;
	}
	
	
	public void add(String username, String points) {
		this.usernames.add(username);
		this.points.add(points);
	}

	public boolean isFromCreator() {
		return fromCreator;
	}

	public void setFromCreator(boolean fromCreator) {
		this.fromCreator = fromCreator;
	}
	
}
