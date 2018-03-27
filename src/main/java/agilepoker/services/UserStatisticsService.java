package agilepoker.services;

import agilepoker.domain.UserStatistics;

public interface UserStatisticsService {	 
    UserStatistics getUserStatisticsById(Integer id);
 
    UserStatistics saveUserStatistics(UserStatistics userStatistics);
    
    
    void deleteUserStatisticsById(Integer id);
}
