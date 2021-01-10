package tr.cobanse.batak.server.action;

import java.util.Collections;

import tr.cobanse.batak.common.Player;
import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.common.ResponseType;
import tr.cobanse.batak.server.GameContext;
import tr.cobanse.batak.server.deck.BatakException;
import tr.cobanse.batak.server.game.BatakGame;
import tr.cobanse.batak.server.game.GameRoom;
import tr.cobanse.batak.server.util.GameExceptionMessage;
import tr.cobanse.batak.server.util.RequestCommandValidator;

public class GuessPointGame implements RequestCommand {

	private RequestCommandValidator requestCommandValidator;
	
	public GuessPointGame(RequestCommandValidator requestCommandValidator) {
		this.requestCommandValidator = requestCommandValidator;
	}

	@Override
	public ResponseMessage execute(RequestMessage requestMessage, GameContext gameContext) {
		requestCommandValidator.validateRequest(requestMessage);
		GameRoom gameRoom = gameContext.findGame(requestMessage.getGameId()).orElseThrow(()->new BatakException(GameExceptionMessage.INVALID_GAME_ROOM));
		String playerName = requestMessage.getPlayerName();
		int guessPoint = requestMessage.getGuessPoint();
		requestCommandValidator.validateGuessPoint(guessPoint);
		BatakGame game = (BatakGame) gameRoom.getGame();
		validateGuessPoint(gameRoom, playerName, guessPoint, game);
		ResponseMessage responseMessage = new ResponseMessage(ResponseType.GUESSPOINT, Collections.emptyList(), Collections.emptyList(), gameRoom.getGameId(), gameRoom.getGame().getPlayers());
		responseMessage.setCurrentPlayer(gameRoom.getGame().currentPlayer());
		return responseMessage;
	}

	private void validateGuessPoint(GameRoom gameRoom, String playerName, int guessPoint, BatakGame game) {
		if(game.getGuessWinnerPoint() <= guessPoint) {
			throw new BatakException(GameExceptionMessage.INVALID_ACTION_IN_GUESS_POINT);
		}
		updateClaimer(gameRoom, playerName, guessPoint, game);
	}

	private void updateClaimer(GameRoom gameRoom, String playerName, int guessPoint, BatakGame game) {
		game.setGuessWinnerPoint(guessPoint);
		Player claimer = gameRoom.getGame().getPlayers().stream().filter(p->p.getPlayerName().equals(playerName)).findFirst().orElseThrow(()->new BatakException(GameExceptionMessage.USER_NOT_FOUND));
		game.setClaimer(claimer);
	}
}
