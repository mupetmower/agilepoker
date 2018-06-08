package agilepoker.domain;

import java.util.HashSet;
import java.util.Set;

//import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class GameSession {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;
    
    private String taskDescription;
    
    @OneToMany(orphanRemoval=true,
    		fetch = FetchType.EAGER,
            mappedBy = "gameSession")
    @Cascade(CascadeType.ALL)
    private Set<User> users = new HashSet<User>();
    
    private boolean showVotes = false;
    
    private String timePassed = "00:00:00";
    private boolean pointTypeIsSize = false;
    

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Set<User> getUsers() {    	
    	return users;
    }
    
    public void setUsers(Set<User> users) {
    	this.users = users;
    }
    
    public void setShowVotes(boolean showVotes) {
		this.showVotes = showVotes;
	}
	
	public boolean getShowVotes() {
		return showVotes;
	}
	
	public void setTimePassed(String timePassed) {
		this.timePassed = timePassed;
	}
	public String getTimePassed() {
		return timePassed;
	}
    
    public void addUser(User user) {
    	user.setGameSession(this);
    	users.add(user);
    	
    }
    
    public void removeUser(User user) {
    	users.remove(user);
    }
    
    public void clearAllVotes() {
    	users.forEach(u -> u.setPoints("-1"));
    }

	public boolean getPointTypeIsSize() {
		return pointTypeIsSize;
	}

	public void setPointTypeIsSize(boolean pointTypeIsSize) {
		this.pointTypeIsSize = pointTypeIsSize;
	}
    
}
