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

public class JoinGameTest {

	private JoinGame joinGame = new JoinGame(new RequestCommandValidator());
	
	@Test
	public void testJoinGameMissingGameId() {
		GameContext gameContext = new GameContext();
		gameContext.addGameRoom(new GameRoom("b7a5b3c6-6407-448e-9cbd-7cfcc3294896"));
		RequestMessage requestMessage = new RequestMessage("selami",RequestType.JOIN);
		BatakException batakException = assertThrows(BatakException.class, ()->joinGame.execute(requestMessage, gameContext));
		assertEquals(batakException.getMessage(), GameExceptionMessage.INVALID_GAME_ROOM);
	}
	
	@Test
	public void testJoinGameInvalidGameId() {
		GameContext gameContext = new GameContext();
		gameContext.addGameRoom(new GameRoom("b7a5b3c6-6407-448e-9cbd-7cfcc3294896"));
		RequestMessage requestMessage = new RequestMessage("selami",RequestType.JOIN);
		requestMessage.setGameId("ABCTEST");
		BatakException batakException = assertThrows(BatakException.class, ()->joinGame.execute(requestMessage, gameContext));
		assertEquals(batakException.getMessage(), GameExceptionMessage.INVALID_GAME_ROOM);
	}
	
	@Test
	public void testJoinGame() {
		GameContext gameContext = new GameContext();
		gameContext.addGameRoom(new GameRoom("b7a5b3c6-6407-448e-9cbd-7cfcc3294896"));
		RequestMessage requestMessage = new RequestMessage("selami",RequestType.JOIN);
		requestMessage.setGameId("b7a5b3c6-6407-448e-9cbd-7cfcc3294896");
		ResponseMessage responseMessage = joinGame.execute(requestMessage, gameContext);
		assertNotNull(responseMessage);
		assertEquals(responseMessage.getResponseType(), ResponseType.JOIN);
		assertEquals(responseMessage.getUsers().size(), 1);
	}
	
	@Test
	public void testJoinGameFullRoom() {
		GameContext gameContext = new GameContext();
		GameRoom gameRoom = new GameRoom("b7a5b3c6-6407-448e-9cbd-7cfcc3294896");
		gameRoom.registerPlayer(new HumanPlayer("1"));
		gameRoom.registerPlayer(new HumanPlayer("2"));
		gameRoom.registerPlayer(new HumanPlayer("3"));
		gameRoom.registerPlayer(new HumanPlayer("4"));
		gameContext.addGameRoom(gameRoom);
		RequestMessage requestMessage = new RequestMessage("selami",RequestType.JOIN);
		requestMessage.setGameId("b7a5b3c6-6407-448e-9cbd-7cfcc3294896");
		BatakException batakException = assertThrows(BatakException.class, ()->joinGame.execute(requestMessage, gameContext));
		assertEquals(batakException.getMessage(), GameExceptionMessage.GAME_ROOM_FULL);
	}
	
	@Test
	public void testJoinGameDuplicatePlayer() {
		GameContext gameContext = new GameContext();
		GameRoom gameRoom = new GameRoom("b7a5b3c6-6407-448e-9cbd-7cfcc3294896");
		gameRoom.registerPlayer(new HumanPlayer("1"));
		gameRoom.registerPlayer(new HumanPlayer("2"));
		gameContext.addGameRoom(gameRoom);
		RequestMessage requestMessage = new RequestMessage("2",RequestType.JOIN);
		requestMessage.setGameId("b7a5b3c6-6407-448e-9cbd-7cfcc3294896");
		BatakException batakException = assertThrows(BatakException.class, ()->joinGame.execute(requestMessage, gameContext));
		assertEquals(batakException.getMessage(), GameExceptionMessage.EXISTING_PLAYER_NAME);
		
//		List<GameRoom> availableGames = g.listGames();
//		GameRoom gameRoom = availableGames.stream().filter(g->g.getGameId().equals(requestMessage.getGameId())).findFirst().orElse(null);
//		if(gameRoom == null) {
//			return new ResponseMessage(GameExceptionMessage.INVALID_GAME_ROOM, ResponseType.ERROR);
//		}
//		if(gameRoom.isFull()) {
//			return new ResponseMessage(GameExceptionMessage.GAME_ROOM_FULL, ResponseType.ERROR);
//		}
//		Player existingPlayerName = gameRoom.getPlayers().stream().filter(p->p.getPlayerName().equalsIgnoreCase(requestMessage.getPlayerName())).findFirst().orElse(null);
//		if(existingPlayerName != null) {
//			return new ResponseMessage(GameExceptionMessage.EXISTING_PLAYER_NAME, ResponseType.ERROR);
//		}
//		gameRoom.registerPlayer(new HumanPlayer(requestMessage.getPlayerName())); 
//		return new ResponseMessage(MESSAGE, Arrays.asList(gameRoom.getGameId()), new ArrayList<Card>(), gameRoom.getGameId(), 
//				gameRoom.getPlayers().stream().map(Player::getPlayerName).collect(Collectors.toList()), ResponseType.JOIN);
	}
}

