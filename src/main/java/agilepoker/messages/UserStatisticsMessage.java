package agilepoker.messages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import agilepoker.domain.User;
import agilepoker.domain.UserStatistics;

public class UserStatisticsMessage {
	
	//private List<UserStatistics> userStatistics;
	
	private Integer userId;
	private String username;
	
	
	private String currentVote;
	private double averagePoints;
	private String averageTime;
	
	private List<String> pastVotes = new ArrayList<String>();
	private Set<String> pastTasks = new HashSet<String>();
	
	private Map<String, String> pointsPerTask;
	
	
//	public void addUserStatistics(UserStatistics userStatistics) {
//		this.userStatistics.add(userStatistics);
//	}
//	
//	public void setUserStatistics(List<UserStatistics> userStatistics) {
//		this.userStatistics = userStatistics;
//	}
//	
//	public List<UserStatistics> getUserStatistics() {
//		return userStatistics;
//	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
	
	public void setCurrentVote(String currentVote) {
		this.currentVote = currentVote;
	}
	
	public String getCurrentVote() {
		return currentVote;
	}
	
	public void setAveragePoints(double averagePoints) {
		this.averagePoints = averagePoints;
	}
	
	public double getAveragePoints() {
		return averagePoints;
	}
	
	public void setAverageTime(String averageTime) {
		this.averageTime = averageTime;
	}
	
	public String getAverageTime() {
		return averageTime;
	}
	
	public void setPastVotes(List<String> pastVotes) {
		this.pastVotes = pastVotes;
	}
	
	public List<String> getPastVotes() {
		return pastVotes;
	}
	
	public void setPastTasks(Set<String> pastTasks) {
		this.pastTasks = pastTasks;
	}
	
	public Set<String> getPastTasks() {
		return pastTasks;
	}
	
	public void addPointsAndTask(String task, String points) {
		pointsPerTask.put(task, points);
	}
	
	public Map<String, String> getPointsPerTask() {
		return pointsPerTask;
	}
	
	public void setPointsPerTask(Map<String, String> pointsPerTask) {
		this.pointsPerTask = pointsPerTask;
	}
	
	public void addPoints(String points) {
		pastVotes.add(points);
	}
	
	public void addTask(String task) {
		pastTasks.add(task);
	}
}
