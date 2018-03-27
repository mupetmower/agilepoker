package agilepoker.domain;

public enum Role {
	Creator,
	Admin,
	User;
	
	
	
	public Role intToRole(int r) {
		switch(r) {
			case 0:
				return Creator;				
			case 1:
				return Admin;
			default:
				return User;
				
		}
	}
	
}
