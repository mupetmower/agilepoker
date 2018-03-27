package agilepoker.services;

import org.springframework.beans.factory.annotation.Autowired;

import agilepoker.domain.User;
import agilepoker.domain.UserStatistics;
import agilepoker.repositories.UserStatisticsRepository;

public class UserStatisticsServiceImpl implements UserStatisticsService {
	
	private UserStatisticsRepository userStatisticsRepository;

	@Autowired
	public void setProductRepository(UserStatisticsRepository userStatisticsRepository) {
	    this.userStatisticsRepository = userStatisticsRepository;
	}
	
	
	@Override
	public UserStatistics getUserStatisticsById(Integer id) {
	    return userStatisticsRepository.findOne(id);
	}
	
	@Override
	public UserStatistics saveUserStatistics(UserStatistics userStatistics) {
	    return userStatisticsRepository.save(userStatistics);
	}
	
	@Override
	public void deleteUserStatisticsById(Integer id) {
		userStatisticsRepository.delete(id);
	}

}
