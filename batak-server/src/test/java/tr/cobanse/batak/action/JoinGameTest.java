package tr.cobanse.batak.action;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.RequestType;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.common.ResponseType;
import tr.cobanse.batak.server.GameContext;
import tr.cobanse.batak.server.action.JoinGame;
import tr.cobanse.batak.server.deck.BatakException;
import tr.cobanse.batak.server.game.GameRoom;
import tr.cobanse.batak.server.game.HumanPlayer;
import tr.cobanse.batak.server.util.GameExceptionMessage;
import tr.cobanse.batak.server.util.RequestCommandValidator;

class JoinGameTest {

	private JoinGame joinGame = new JoinGame(new RequestCommandValidator());
	
	@Test
	void testJoinGameMissingGameId() {
		GameContext gameContext = new GameContext();
		gameContext.addGameRoom(new GameRoom("b7a5b3c6-6407-448e-9cbd-7cfcc3294896"));
		RequestMessage requestMessage = new RequestMessage("selami",RequestType.JOIN);
		BatakException batakException = assertThrows(BatakException.class, ()->joinGame.execute(requestMessage, gameContext));
		assertEquals(GameExceptionMessage.INVALID_GAME_ROOM, batakException.getMessage());
	}
	
	@Test
	void testJoinGameInvalidGameId() {
		GameContext gameContext = new GameContext();
		gameContext.addGameRoom(new GameRoom("b7a5b3c6-6407-448e-9cbd-7cfcc3294896"));
		RequestMessage requestMessage = new RequestMessage("selami",RequestType.JOIN);
		requestMessage.setGameId("ABCTEST");
		BatakException batakException = assertThrows(BatakException.class, ()->joinGame.execute(requestMessage, gameContext));
		assertEquals(GameExceptionMessage.INVALID_GAME_ROOM, batakException.getMessage());
	}
	
	@Test
	void testJoinGame() {
		GameContext gameContext = new GameContext();
		gameContext.addGameRoom(new GameRoom("b7a5b3c6-6407-448e-9cbd-7cfcc3294896"));
		RequestMessage requestMessage = new RequestMessage("selami",RequestType.JOIN);
		requestMessage.setGameId("b7a5b3c6-6407-448e-9cbd-7cfcc3294896");
		ResponseMessage responseMessage = joinGame.execute(requestMessage, gameContext);
		assertNotNull(responseMessage);
		assertEquals(ResponseType.JOIN, responseMessage.getResponseType());
		assertEquals(1, responseMessage.getPlayers().size());
	}
	
	@Test
	void testJoinGameFullRoom() {
		GameContext gameContext = new GameContext();
		GameRoom gameRoom = new GameRoom("b7a5b3c6-6407-448e-9cbd-7cfcc3294896");
		gameRoom.getGame().addPlayer(new HumanPlayer("1"));
		gameRoom.getGame().addPlayer(new HumanPlayer("2"));
		gameRoom.getGame().addPlayer(new HumanPlayer("3"));
		gameRoom.getGame().addPlayer(new HumanPlayer("4"));
		gameContext.addGameRoom(gameRoom);
		RequestMessage requestMessage = new RequestMessage("selami",RequestType.JOIN);
		requestMessage.setGameId("b7a5b3c6-6407-448e-9cbd-7cfcc3294896");
		BatakException batakException = assertThrows(BatakException.class, ()->joinGame.execute(requestMessage, gameContext));
		assertEquals(GameExceptionMessage.GAME_ROOM_FULL, batakException.getMessage());
	}
	
	@Test
	void testJoinGameDuplicatePlayer() {
		GameContext gameContext = new GameContext();
		GameRoom gameRoom = new GameRoom("b7a5b3c6-6407-448e-9cbd-7cfcc3294896");
		gameRoom.getGame().addPlayer(new HumanPlayer("1"));
		gameRoom.getGame().addPlayer(new HumanPlayer("2"));
		gameContext.addGameRoom(gameRoom);
		RequestMessage requestMessage = new RequestMessage("2",RequestType.JOIN);
		requestMessage.setGameId("b7a5b3c6-6407-448e-9cbd-7cfcc3294896");
		BatakException batakException = assertThrows(BatakException.class, ()->joinGame.execute(requestMessage, gameContext));
		assertEquals(GameExceptionMessage.EXISTING_PLAYER_NAME, batakException.getMessage());
	}
}

