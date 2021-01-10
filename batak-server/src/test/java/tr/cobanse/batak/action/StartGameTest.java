package tr.cobanse.batak.action;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.RequestType;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.common.ResponseType;
import tr.cobanse.batak.server.GameContext;
import tr.cobanse.batak.server.action.StartGame;
import tr.cobanse.batak.server.util.JsonUtil;
import tr.cobanse.batak.server.util.RequestCommandValidator;
import tr.cobanse.batak.util.GameTestUtils;

public class StartGameTest {

	StartGame actionCreateGame = new StartGame(new RequestCommandValidator());
	
	@Test
	public void startGameTest() {
		String gameId = UUID.randomUUID().toString();
		RequestMessage requestMessage = new RequestMessage("1", RequestType.STARTGAME);
		requestMessage.setGameId(gameId); 
		GameContext gameContext = GameTestUtils.createGameRoom(gameId);
		ResponseMessage responseMessage = actionCreateGame.execute(requestMessage, gameContext);
		System.out.println(JsonUtil.toJson(responseMessage));
		assertNotNull(responseMessage);
		assertEquals(ResponseType.STARTGAME, responseMessage.getResponseType());
		assertNotNull(responseMessage.getAvailableGames()); 
		assertFalse(responseMessage.getAvailableGames().isEmpty(),"GameList is empty");
		assertEquals(gameId, responseMessage.getAvailableGames().get(0),"GameList does not return expected game id ");
		assertTrue(responseMessage.getPlayers().stream().filter(p->p.getPlayerName().equals("1")).findFirst().get().isReady(), "User should be ready");
	}
}
