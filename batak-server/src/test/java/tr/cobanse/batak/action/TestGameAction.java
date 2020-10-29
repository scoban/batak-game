package tr.cobanse.batak.action;

import static org.hamcrest.CoreMatchers.instanceOf;

import org.junit.Assert;
import org.junit.Test;

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
		Assert.assertThat(action, instanceOf(JoinGame.class));
	}
	
	@Test
	public void testChat() {
		PlayerAction action = ClientActionFactory.createAction(RequestType.CHAT);
		Assert.assertThat(action, instanceOf(SendMessage.class));
	}
	
	@Test
	public void testDiscardCard() {
		PlayerAction action = ClientActionFactory.createAction(RequestType.DISCARD);
		Assert.assertThat(action, instanceOf(DiscardCard.class));
	}
	
	@Test
	public void testCreateGame() {
		PlayerAction action = ClientActionFactory.createAction(RequestType.CREATEGAME);
		Assert.assertThat(action, instanceOf(CreateGame.class));
	}
	
	@Test
	public void testLeaveGame() {
		PlayerAction action = ClientActionFactory.createAction(RequestType.LEAVE);
		Assert.assertThat(action, instanceOf(LeaveGame.class));
	}
}
