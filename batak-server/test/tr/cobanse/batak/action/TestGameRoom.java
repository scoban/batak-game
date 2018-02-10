package tr.cobanse.batak.action;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import tr.cobanse.batak.common.CardGame;
import tr.cobanse.batak.common.RequestType;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.server.action.ClientActionFactory;
import tr.cobanse.batak.server.action.CreateGame;
import tr.cobanse.batak.server.action.LeaveGame;
import tr.cobanse.batak.server.action.ListGame;
import tr.cobanse.batak.server.action.PlayerAction;
import tr.cobanse.batak.server.game.BatakGame;
import tr.cobanse.batak.server.game.GameRoom;

public class TestGameRoom {

	@Test
	public void testGetGameRoom() {
		GameRoom room = GameRoom.getInstance();
		room.createGame(new BatakGame());
		Assert.assertEquals("can not create game ",1, room.getNumberOfGame());
	}
	
	@Test
	public void testGetGameRoom1() {
		GameRoom room = GameRoom.getInstance();
		room.createGame(new BatakGame());
		room.createGame(new BatakGame());
		Assert.assertEquals("can not create game ",2, room.getNumberOfGame());
	}
	
	@Test(expected=java.lang.UnsupportedOperationException.class)
	public void testAddGame() {
		GameRoom room = GameRoom.getInstance();
		List<CardGame> game = room.getAvailableGames();
		game.add(new BatakGame());
	}
	
	@Test
	public void testCloseGame() {
		GameRoom room = GameRoom.getInstance();
		room.createGame(new BatakGame());
		List<CardGame> game = room.getAvailableGames();
		room.closeGame(game.get(0)); 
		Assert.assertEquals("can not create game ",0, room.getNumberOfGame());
	}
	
	@Test
	public void testListGame() {
		GameRoom room = GameRoom.getInstance();
		room.createGame(new BatakGame());
		PlayerAction action = ClientActionFactory.createAction(RequestType.LISTGAME);
		Assert.assertThat(action, instanceOf(ListGame.class));
		ResponseMessage message = action.execute();
		Assert.assertEquals("list game encounter an error ",1, message.getAvailableGames().size()); 
	}
	
	@Test
	public void testCreateGame() {
		PlayerAction action = ClientActionFactory.createAction(RequestType.CREATEGAME);
		Assert.assertThat(action, instanceOf(CreateGame.class));
		ResponseMessage message = action.execute();
		Assert.assertEquals("list game encounter an error ",1, message.getAvailableGames().size());
		Assert.assertNotNull("create game encounter an error ",message.getCardGame());
	}
}
