package tr.cobanse.batak.server.action;

import java.util.Collections;
import java.util.List;

import tr.cobanse.batak.common.Card;
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

public class DiscardCard implements RequestCommand {

	private RequestCommandValidator requestCommandValidator;
	
	public DiscardCard(RequestCommandValidator requestCommandValidator) {
		this.requestCommandValidator = requestCommandValidator;
	}

	@Override
	public ResponseMessage execute(RequestMessage requestMessage, GameContext gameContext) {
		requestCommandValidator.validateRequest(requestMessage);
		GameRoom gameRoom = gameContext.findGame(requestMessage.getGameId()).orElseThrow(()->new BatakException(GameExceptionMessage.INVALID_GAME_ROOM));
		String playerName = requestMessage.getPlayerName();
		Card card = requestMessage.getCard();
		requestCommandValidator.validateInTurnUser(playerName, gameRoom);
		gameRoom.drawCard(playerName, card);
		BatakGame game = (BatakGame) gameRoom.getGame();
		game.updatePlayerInTurn();
		Player player = game.getPlayers().stream().filter(p->p.getPlayerName().equals(playerName)).findFirst().orElseThrow(()->new BatakException(GameExceptionMessage.USER_NOT_FOUND));
		game.addGameRound(card, player);
		List<Card> cards = gameRoom.getPlayerCards(playerName);
		ResponseMessage responseMessage = new ResponseMessage(ResponseType.DISCARD, Collections.emptyList(), cards, gameRoom.getGameId(), gameRoom.getGame().getPlayers());
		responseMessage.setCurrentPlayer(gameRoom.getGame().currentPlayer());
		responseMessage.setCardsInPool(game.getCurrentGameRound());
		return responseMessage;
	}
}
