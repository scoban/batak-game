package tr.cobanse.batak.action;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.RequestType;
import tr.cobanse.batak.server.action.CreateGame;
import tr.cobanse.batak.server.action.DiscardCard;
import tr.cobanse.batak.server.action.JoinGame;
import tr.cobanse.batak.server.action.LeaveGame;
import tr.cobanse.batak.server.action.RequestCommand;
import tr.cobanse.batak.server.action.SendMessage;
import tr.cobanse.batak.server.action.factory.RequestProcessorFactory;

public class TestGameActionFactory {
	
	@Test
	public void testJoinGame() {
		RequestMessage message = new RequestMessage("", RequestType.JOIN); 
		RequestCommand action = RequestProcessorFactory.getProcessor(message);
		assertTrue(action instanceof JoinGame,"");
	}
	
	@Test
	public void testChat() {
		RequestMessage message = new RequestMessage("", RequestType.CHAT);
		RequestCommand action = RequestProcessorFactory.getProcessor(message);
		assertTrue(action instanceof SendMessage,"");
	}
	
	@Test
	public void testDiscardCard() {
		RequestMessage message = new RequestMessage("", RequestType.DISCARD);
		RequestCommand action = RequestProcessorFactory.getProcessor(message);
		assertTrue(action instanceof DiscardCard,"");
	}
	
	@Test
	public void testCreateGame() {
		RequestMessage message = new RequestMessage("", RequestType.CREATEGAME);
		RequestCommand action = RequestProcessorFactory.getProcessor(message);
		assertTrue(action instanceof CreateGame,"");
	}
	
	@Test
	public void testLeaveGame() {
		RequestMessage message = new RequestMessage("", RequestType.LEAVE);
		RequestCommand action = RequestProcessorFactory.getProcessor(message);
		assertTrue(action instanceof LeaveGame,"");
	}
}
