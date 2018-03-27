package agilepoker.services;

import agilepoker.domain.GameSession;

public interface GameSessionService {
	Iterable<GameSession> listAllSessions();
	
	 
	GameSession getSessionById(Integer id);
 
    GameSession saveSession(GameSession gameSession);
    
    void deleteSession(Integer id);
}
