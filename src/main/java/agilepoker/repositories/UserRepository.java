package agilepoker.repositories;

import org.springframework.data.repository.CrudRepository;

import agilepoker.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}

