package tr.cobanse.batak.server;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import tr.cobanse.batak.server.game.GameRoom;

public class GameContext {

	private Map<String, GameRoom> gameRoomContainer = new HashMap<>();
	
	public GameContext() {
	}
	
	public List<GameRoom> listGames() {
		return Collections.unmodifiableList(gameRoomContainer.values().stream().collect(Collectors.toList()));
	}
	
	public void addGameRoom(GameRoom gameRoom) {
		gameRoomContainer.put(gameRoom.getGameId(), gameRoom);
	}

	public Optional<GameRoom> findGame(String gameId) {
		return listGames().stream().filter(g->g.getGameId().equals(gameId)).findFirst();
	}
}
