package tr.cobanse.batak.server.util;

import org.apache.commons.lang3.StringUtils;

import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.server.deck.BatakException;
import tr.cobanse.batak.server.game.GameRoom;

public class RequestCommandValidator {

	
	private static final int MAX_GUESS_POINT = 13;

	public void validateRequest(RequestMessage requestMessage) {
		String gameToBeJoined = requestMessage.getGameId();
		if(StringUtils.isBlank(gameToBeJoined)) {
			throw new BatakException(GameExceptionMessage.INVALID_GAME_ROOM);
		}
	}

	public void validateInTurnUser(String playerName, GameRoom gameRoom) {
		if(gameRoom.getGame().currentPlayer().getPlayerName().equals(playerName) == false) {
			throw new BatakException(GameExceptionMessage.INVALID_ACTION_IN_TURN);
		}
	}

	public void validateGuessPoint(int guessPoint) {
		if(guessPoint > MAX_GUESS_POINT) {
			throw new BatakException(GameExceptionMessage.INVALID_ACTION_IN_GUESS_POINT);
		}
	}
}
