package tr.cobanse.batak.server.action.factory;

import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.RequestType;
import tr.cobanse.batak.server.action.CreateGame;
import tr.cobanse.batak.server.action.DiscardCard;
import tr.cobanse.batak.server.action.EchoLoopMessage;
import tr.cobanse.batak.server.action.JoinGame;
import tr.cobanse.batak.server.action.LeaveGame;
import tr.cobanse.batak.server.action.ListGame;
import tr.cobanse.batak.server.action.NullType;
import tr.cobanse.batak.server.action.RequestCommand;
import tr.cobanse.batak.server.action.SendMessage;
import tr.cobanse.batak.server.util.RequestCommandValidator;

public class RequestProcessorFactory {

	private RequestProcessorFactory() {
	}
	
	public static RequestCommand getProcessor(RequestMessage message) {
		
		if(message==null)
			return new NullType();
		
		if(message.getRequestType().equals(RequestType.LISTGAME)) {
			return new ListGame();
		}
		if(message.getRequestType().equals(RequestType.ECHO)) {
			return new EchoLoopMessage();
		}
		if(message.getRequestType().equals(RequestType.JOIN)) {
			return new JoinGame(new RequestCommandValidator());
		}
		if(message.getRequestType().equals(RequestType.LEAVE)) {
			return new LeaveGame(new RequestCommandValidator());
		}
		if(message.getRequestType().equals(RequestType.CHAT)) {
			return new SendMessage();
		}
		if(message.getRequestType().equals(RequestType.DISCARD)) {
			return new DiscardCard(new RequestCommandValidator());
		}
		if(message.getRequestType().equals(RequestType.CREATEGAME)) {
			return new CreateGame();
		}
		return null;
	}

}
