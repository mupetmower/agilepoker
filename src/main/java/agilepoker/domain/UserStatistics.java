package agilepoker.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Version;



@Entity
public class UserStatistics {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;
	
	@OneToOne(fetch = FetchType.EAGER)
	private User user;
	
	private String currentVote;
	
	
	
	private double averagePoints;
	
	
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> points;
	
	private String averageTime;
	

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> pastTasks;
	
	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, String> taskWithPoints;
	
	
	
//	public void updateAveragePoints(String newTask, int newVote) {
//		pointsPerTask.put(newTask, newVote);
//		int total = 0;
//		OptionalDouble avg = pointsPerTask.values().stream().mapToInt(i -> i.intValue()).average();
//		averagePoints = avg.getAsDouble();
//		System.out.println(averagePoints);
//	}
	
	
	public void updateAveragePoints(String newTask, String newVote) {
		points.add(newVote);
		//int total = 0;
		OptionalDouble avg = points.stream().mapToInt(s -> Integer.parseInt(s)).average();
		averagePoints = avg.getAsDouble();
		
		System.out.println(averagePoints);
		
		pastTasks.add(newTask);
		
		taskWithPoints.put(newTask, newVote);
		///pointsPerTask.add(Pair.of(newTask, newVote));
	}
	
	
	
	private int secondsFromZero(String timeString) {
		int seconds = 0;
		
		
		
		return seconds;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
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
	
	public void setPoints(List<String> points) {
		this.points = points;
	}
	
	public List<String> getPoints() {
		return points;
	}
	
	public List<String> getPointsAsStrings() {
		List<String> p = new ArrayList<String>();
		points.forEach(i -> p.add(i.toString()));
		return p;
	}
	
	public Set<String> getPastTasks() {
		return pastTasks;
	}
	
	public void setTaskWithPoints(Map<String, String> taskWithPoints) {
		this.taskWithPoints = taskWithPoints;
	}
	
	public Map<String, String> getTaskWithPoints() {
		return taskWithPoints;
	}
	
}
