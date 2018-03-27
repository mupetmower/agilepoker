package agilepoker.repositories;

import org.springframework.data.repository.CrudRepository;

import agilepoker.domain.User;
import agilepoker.domain.UserStatistics;

public interface UserStatisticsRepository extends CrudRepository<UserStatistics, Integer> {

}
