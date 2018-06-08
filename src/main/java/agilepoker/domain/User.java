package agilepoker.domain;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn()
    private GameSession gameSession;
    
    @Column
    @NotNull(message = "error.username.notnull")
    @Size(min = 1, max = 30, message = "error.username.size")
    private String username;
    
    private String points = "-1";
    
    private Role role = Role.User;

    private boolean hasVoted = false;
    
    
    private boolean isInSession;

    @OneToOne(orphanRemoval=true,
    		fetch = FetchType.EAGER,
    		cascade = CascadeType.ALL)
    @JoinColumn()
    private UserStatistics userStatistics;
    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getInitialUsername() {
    	return "GuestUser" + getId().toString();
    }
    
    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
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
    
    public GameSession getGameSession() {
        return gameSession;
    }

    public void setGameSession(GameSession gameSession) {
        this.gameSession = gameSession;
    }
    
    public boolean getHasVoted() {
        return hasVoted;
    }

    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }
    
    public Role getRole() {
    	return role;
    }
    
    public void setRole(Role role) {
    	this.role = role;
    }
    
    public void setUserStatistics(UserStatistics userStatistics) {
    	this.userStatistics = userStatistics;
    }
    
    public UserStatistics getUserStatistics() {
    	return userStatistics;
    }
    
    public void setIsInSession(boolean isInSession) {
    	this.isInSession = isInSession;
    }
        
    public boolean getIsInsession() {
    	return isInSession;
    }
    
    
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User )) return false;
        return id != null && id.equals(((User) o).id);
    }
    @Override
    public int hashCode() {
        return 123;
    }
}

