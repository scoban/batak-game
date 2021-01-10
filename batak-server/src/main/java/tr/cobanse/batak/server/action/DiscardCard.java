package tr.cobanse.batak.server.action;

import java.util.Collections;
import java.util.List;

import tr.cobanse.batak.common.Card;
import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.common.ResponseType;
import tr.cobanse.batak.server.GameContext;
import tr.cobanse.batak.server.deck.BatakException;
import tr.cobanse.batak.server.game.GameRoom;
import tr.cobanse.batak.server.util.GameExceptionMessage;
import tr.cobanse.batak.server.util.RequestCommandValidator;

public class DiscardCard implements RequestCommand {

//	private GameRuleController gameRuleController;
	private RequestCommandValidator requestCommandValidator;
	
	public DiscardCard(RequestCommandValidator requestCommandValidator) {
		this.requestCommandValidator = requestCommandValidator;
	}

	@Override
	public ResponseMessage execute(RequestMessage requestMessage, GameContext gameContext) {
		requestCommandValidator.validateRequest(requestMessage);
		GameRoom gameRoom = gameContext.findGame(requestMessage.getGameId()).orElseThrow(()->new BatakException(GameExceptionMessage.INVALID_GAME_ROOM));
		String player = requestMessage.getPlayerName();
		Card card = requestMessage.getCard();
		gameRoom.drawCard(player, card);
		List<Card> cards = gameRoom.getPlayerCards(player);
		return new ResponseMessage(ResponseType.DISCARD, Collections.emptyList(), cards, gameRoom.getGameId(), gameRoom.getGame().getPlayers()); 
	}

}
