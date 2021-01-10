package tr.cobanse.batak.util;

import tr.cobanse.batak.server.GameContext;
import tr.cobanse.batak.server.game.GameRoom;
import tr.cobanse.batak.server.game.HumanPlayer;

public class GameTestUtils {

	public static GameContext createGameRoom(String gameId) {
		GameContext gameContext = new GameContext();
		GameRoom gameRoom = new GameRoom(gameId);
		gameRoom.getGame().addPlayer(new HumanPlayer("1"));
		gameRoom.getGame().addPlayer(new HumanPlayer("2"));
		gameRoom.getGame().addPlayer(new HumanPlayer("3"));
		gameRoom.getGame().addPlayer(new HumanPlayer("4"));
//		cardDistributeService.distributeCards(gameRoom);
		gameContext.addGameRoom(gameRoom);
		return gameContext;
	}
}
