package agilepoker.messages;

import java.util.ArrayList;
import java.util.List;

public class KickListMessage {
	private List<Integer> kickList = new ArrayList<Integer>();
	
	public void setkickList(List<Integer> kickList) {
		this.kickList = kickList;
	}
	
	public List<Integer> getKickList() {
		return kickList;
	}
	
	public void addUserIdToKickList(int userId) {
		kickList.add(userId);
	}
 }
