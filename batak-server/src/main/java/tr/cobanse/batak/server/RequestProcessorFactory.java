package tr.cobanse.batak.server;

import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.RequestType;
import tr.cobanse.batak.server.action.ListGame;
import tr.cobanse.batak.server.action.NullType;
import tr.cobanse.batak.server.action.PlayerAction;

public class RequestProcessorFactory {

	public static PlayerAction getProcessor(RequestMessage message) {
		if(message==null)
			return new NullType();
		
		if(message.getRequestType().equals(RequestType.LISTGAME)) {
			return new ListGame();
		}
		return null;
	}

}
