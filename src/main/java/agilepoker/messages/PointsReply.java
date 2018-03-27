package agilepoker.messages;

import java.util.ArrayList;
import java.util.List;

public class PointsReply {

	private List<String> usernames = new ArrayList<String>();
	private List<Integer> points = new ArrayList<Integer>();
	
	private boolean showVotes = false;
	
	public void setUsernames(List<String> usernames) {
		this.usernames = usernames;
	}
	
	public List<String> getUsernames() {
		return usernames;
	}
	
	public void setPoints(List<Integer> points) {
		this.points = points;
	}
	
	public List<Integer> getPoints() {
		return points;
	}
	
	public void setShowVotes(boolean showVotes) {
		this.showVotes = showVotes;
	}
	
	public boolean getShowVotes() {
		return showVotes;
	}
	
	
	public void add(String username, int points) {
		this.usernames.add(username);
		this.points.add(points);
	}
	
}
