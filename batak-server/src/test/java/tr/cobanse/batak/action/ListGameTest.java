package tr.cobanse.batak.action;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.RequestType;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.server.GameContext;
import tr.cobanse.batak.server.action.ListGame;
import tr.cobanse.batak.util.GameTestUtils;

public class ListGameTest {

	ListGame actionCreateGame = new ListGame();
	
	@Test
	public void listGameTest() {
		RequestMessage requestMessage = new RequestMessage("1", RequestType.LISTGAME);
		String gameId = UUID.randomUUID().toString();
		GameContext gameContext = GameTestUtils.createGameRoom(gameId);
		ResponseMessage responseMessage = actionCreateGame.execute(requestMessage, gameContext);
		assertNotNull(responseMessage);
		assertNotNull(responseMessage.getAvailableGames()); 
		assertFalse(responseMessage.getAvailableGames().isEmpty(),"GameList is empty");
		assertEquals(gameId, responseMessage.getAvailableGames().get(0),"GameList does not return expected game id ");
	}
}
