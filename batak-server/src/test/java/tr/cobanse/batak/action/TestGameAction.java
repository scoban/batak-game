package tr.cobanse.batak.action;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import tr.cobanse.batak.common.RequestType;
import tr.cobanse.batak.server.action.ClientActionFactory;
import tr.cobanse.batak.server.action.CreateGame;
import tr.cobanse.batak.server.action.DiscardCard;
import tr.cobanse.batak.server.action.JoinGame;
import tr.cobanse.batak.server.action.LeaveGame;
import tr.cobanse.batak.server.action.PlayerAction;
import tr.cobanse.batak.server.action.SendMessage;

public class TestGameAction {
	
	@Test
	public void testJoinGame() {
		PlayerAction action = ClientActionFactory.createAction(RequestType.JOIN);
		assertTrue(action instanceof JoinGame,"");
	}
	
	@Test
	public void testChat() {
		PlayerAction action = ClientActionFactory.createAction(RequestType.CHAT);
		assertTrue(action instanceof SendMessage,"");
	}
	
	@Test
	public void testDiscardCard() {
		PlayerAction action = ClientActionFactory.createAction(RequestType.DISCARD);
		assertTrue(action instanceof DiscardCard,"");
	}
	
	@Test
	public void testCreateGame() {
		PlayerAction action = ClientActionFactory.createAction(RequestType.CREATEGAME);
		assertTrue(action instanceof CreateGame,"");
	}
	
	@Test
	public void testLeaveGame() {
		PlayerAction action = ClientActionFactory.createAction(RequestType.LEAVE);
		assertTrue(action instanceof LeaveGame,"");
	}
}
