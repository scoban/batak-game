package tr.cobanse.batak.action;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.RequestType;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.common.ResponseType;
import tr.cobanse.batak.server.GameContext;
import tr.cobanse.batak.server.action.LeaveGame;
import tr.cobanse.batak.server.deck.BatakException;
import tr.cobanse.batak.server.game.GameRoom;
import tr.cobanse.batak.server.game.HumanPlayer;
import tr.cobanse.batak.server.util.GameExceptionMessage;
import tr.cobanse.batak.server.util.RequestCommandValidator;

public class LeaveGameTest {

	private LeaveGame leaveGame = new LeaveGame(new RequestCommandValidator());
	
	@Test
	public void testLeaveGameUserNotFound() {
		GameContext gameContext = new GameContext();
		GameRoom gameRoom = new GameRoom("b7a5b3c6-6407-448e-9cbd-7cfcc3294896");
		gameRoom.getGame().addPlayer(new HumanPlayer("1"));
		gameContext.addGameRoom(gameRoom);
		RequestMessage requestMessage = new RequestMessage("2",RequestType.LEAVE);
		requestMessage.setGameId("b7a5b3c6-6407-448e-9cbd-7cfcc3294896");
		BatakException batakException = assertThrows(BatakException.class, ()->leaveGame.execute(requestMessage, gameContext));
		assertEquals(GameExceptionMessage.USER_NOT_FOUND, batakException.getMessage());
	}
	
	@Test
	public void testLeaveGameUser() {
		GameContext gameContext = new GameContext();
		GameRoom gameRoom = new GameRoom("b7a5b3c6-6407-448e-9cbd-7cfcc3294896");
		gameRoom.getGame().addPlayer(new HumanPlayer("1"));
		gameContext.addGameRoom(gameRoom);
		RequestMessage requestMessage = new RequestMessage("1",RequestType.LEAVE);
		requestMessage.setGameId("b7a5b3c6-6407-448e-9cbd-7cfcc3294896");
		ResponseMessage responseMessage = leaveGame.execute(requestMessage, gameContext);
		assertEquals(ResponseType.LEAVEGAME, responseMessage.getResponseType());
		assertEquals(0, responseMessage.getPlayers().size()); 
	}
}
