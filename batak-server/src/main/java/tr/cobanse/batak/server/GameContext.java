package tr.cobanse.batak.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import tr.cobanse.batak.server.game.GameRoom;

public class GameContext {

	private Map<String, GameRoom> gameRoomContainer = new HashMap<>();
	
	public GameContext() {
	}
	
	public List<GameRoom> listGames() {
		return new ArrayList<>(gameRoomContainer.values());
	}
	
	public void addGameRoom(GameRoom gameRoom) {
		gameRoomContainer.put("Game-1", gameRoom);
	}

	public Optional<GameRoom> findGame(String gameId) {
		return listGames().stream().filter(g->g.getGameId().equals(gameId)).findFirst();
	}
}
