package agilepoker.services;

import agilepoker.domain.User;

public interface UserService {
	Iterable<User> listAllUsers();
	 
    User getUserById(Integer id);
 
    User saveUser(User user);
    
    
    void deleteUser(Integer id);
}
