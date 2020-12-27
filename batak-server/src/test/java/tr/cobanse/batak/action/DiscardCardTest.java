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
import tr.cobanse.batak.server.game.HumanPlayer;
import tr.cobanse.batak.server.game.controller.CardDistributeService;
import tr.cobanse.batak.server.game.controller.CardDistributeServiceImpl;
import tr.cobanse.batak.server.util.RequestCommandValidator;

public class DiscardCardTest {

	private static final Integer MAX_PLAYER = 4;
	private DiscardCard discardCard = new DiscardCard(new RequestCommandValidator());
	private CardDistributeService cardDistributeService = new CardDistributeServiceImpl();
	@Test
	public void testDiscardCardGame() {
		GameContext gameContext = new GameContext();
		GameRoom gameRoom = new GameRoom("b7a5b3c6-6407-448e-9cbd-7cfcc3294896");
		gameRoom.registerPlayer(new HumanPlayer("1"));
		gameRoom.registerPlayer(new HumanPlayer("2"));
		gameRoom.registerPlayer(new HumanPlayer("3"));
		gameRoom.registerPlayer(new HumanPlayer("4"));
		cardDistributeService.distributeCards(gameRoom);
		gameContext.addGameRoom(gameRoom);
		
		RequestMessage requestMessage = new RequestMessage("1", RequestType.DISCARD);
		requestMessage.setGameId(gameRoom.getGameId());
		requestMessage.setCard(new Card(Symbol.SINEK, CardType.AS));
		ResponseMessage responseMessage = discardCard.execute(requestMessage, gameContext);
		assertNotNull(responseMessage);
		assertEquals(responseMessage.getResponseType(), ResponseType.DISCARD);
		assertEquals(responseMessage.getUsers().size(), MAX_PLAYER);
	}
	
}

