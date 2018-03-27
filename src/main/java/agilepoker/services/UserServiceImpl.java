package agilepoker.services;

import org.springframework.beans.factory.annotation.Autowired;

import agilepoker.domain.User;
import agilepoker.repositories.UserRepository;

public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	public void setProductRepository(UserRepository userRepository) {
	    this.userRepository = userRepository;
	}
	
	@Override
	public Iterable<User> listAllUsers() {
	    return userRepository.findAll();
	}
	
	@Override
	public User getUserById(Integer id) {
	    return userRepository.findOne(id);
	}
	
	@Override
	public User saveUser(User user) {
	    return userRepository.save(user);
	}
	
	@Override
	public void deleteUser(Integer id) {
		userRepository.delete(id);
	}
}
