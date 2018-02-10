package tr.cobanse.batak.server.action;

import java.util.List;

import tr.cobanse.batak.common.CardGame;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.server.game.BatakGame;
import tr.cobanse.batak.server.game.GameRoom;

/**
 * @author coban
 * This action is constructed upon user request type CRATEGAME
 * Once a user request to create a new game, it
 */
public class CreateGame implements PlayerAction{

	@Override
	public ResponseMessage execute() {
		CardGame newGame = new BatakGame();
		GameRoom.getInstance().createGame(newGame);
		List<CardGame> availableGames = GameRoom.getInstance().getAvailableGames();
		return new ResponseMessage("new game created", availableGames, null, newGame);
	}

}
