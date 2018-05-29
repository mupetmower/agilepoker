package agilepoker.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import agilepoker.domain.GameSession;
import agilepoker.repositories.GameSessionRepository;

public class GameSessionServiceImpl implements GameSessionService {
	private GameSessionRepository sessionRepository;

	@Autowired
	public void setSessionRepository(GameSessionRepository sessionRepository) {
	    this.sessionRepository = sessionRepository;
	}
	
	@Override
	public Iterable<GameSession> listAllSessions() {
	    return sessionRepository.findAll();
	}
	
	
	
	@Override
	public GameSession getSessionById(Integer id) {
	    return sessionRepository.findOne(id);
	}
	
	@Transactional
	@Override
	public GameSession saveSession(GameSession gameSession) {
	    return sessionRepository.save(gameSession);
	}
	
	@Override
	public void deleteSession(Integer id) {
		sessionRepository.delete(id);
	}
}
