package agilepoker.repositories;

import org.springframework.data.repository.CrudRepository;

import agilepoker.domain.GameSession;

public interface GameSessionRepository extends CrudRepository<GameSession, Integer> {

}
