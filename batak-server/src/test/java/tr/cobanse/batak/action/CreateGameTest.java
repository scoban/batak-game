package tr.cobanse.batak.action;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.RequestType;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.server.GameContext;
import tr.cobanse.batak.server.action.CreateGame;

public class CreateGameTest {

	CreateGame actionCreateGame = new CreateGame();
	
	@Test
	public void createGameTest() {
		RequestMessage requestMessage = new RequestMessage("1", RequestType.CREATEGAME);
		GameContext gameContext = new GameContext();
		ResponseMessage responseMessage = actionCreateGame.execute(requestMessage, gameContext);
		assertNotNull(responseMessage);
		assertNotNull(responseMessage.getAvailableGames()); 
		assertFalse(responseMessage.getAvailableGames().isEmpty(),"Game could not created");
	}
}
