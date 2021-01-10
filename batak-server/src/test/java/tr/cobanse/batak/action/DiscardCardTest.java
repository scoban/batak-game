package tr.cobanse.batak.action;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import tr.cobanse.batak.common.Card;
import tr.cobanse.batak.common.CardType;
import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.RequestType;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.common.ResponseType;
import tr.cobanse.batak.common.Symbol;
import tr.cobanse.batak.server.GameContext;
import tr.cobanse.batak.server.action.DiscardCard;
import tr.cobanse.batak.server.game.GameRoom;
import tr.cobanse.batak.server.util.RequestCommandValidator;
import tr.cobanse.batak.util.GameTestUtils;

public class DiscardCardTest {

	private static final Integer MAX_PLAYER = 4;
	private DiscardCard discardCard = new DiscardCard(new RequestCommandValidator());
//	private CardDistributeService cardDistributeService = new CardDistributeServiceImpl();
	
	@Test
	public void testDiscardCardGame() {
		GameContext gameContext = GameTestUtils.createGameRoom("b7a5b3c6-6407-448e-9cbd-7cfcc3294896");
		GameRoom gameRoom = gameContext.findGame("b7a5b3c6-6407-448e-9cbd-7cfcc3294896").get();
		Card card = new Card(Symbol.SINEK, CardType.AS);
		
		gameRoom.getGame().getPlayers().get(0).drawCard(card);
		gameRoom.getGame().getPlayers().get(0).drawCard(new Card(Symbol.SINEK, CardType.EIGHT));
		
		RequestMessage requestMessage = new RequestMessage("1", RequestType.DISCARD);
		requestMessage.setGameId(gameRoom.getGameId());
		requestMessage.setCard(card);
		ResponseMessage responseMessage = discardCard.execute(requestMessage, gameContext);
		assertNotNull(responseMessage);
		assertEquals(ResponseType.DISCARD, responseMessage.getResponseType());
		assertEquals(MAX_PLAYER, responseMessage.getPlayers().size());
		assertEquals(1, responseMessage.getAvailableCards().size());
	}
	
}

