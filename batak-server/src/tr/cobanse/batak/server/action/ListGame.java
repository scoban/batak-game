package tr.cobanse.batak.server.action;

import java.util.List;

import tr.cobanse.batak.common.CardGame;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.server.game.GameRoom;

public class ListGame implements PlayerAction{

	@Override
	public ResponseMessage execute() {
		List<CardGame> availableGames = GameRoom.getInstance().getAvailableGames();
		return new ResponseMessage("available games", availableGames, null);
	}

}
