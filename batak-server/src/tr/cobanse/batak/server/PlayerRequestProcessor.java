package tr.cobanse.batak.server;

import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.server.action.PlayerAction;
import tr.cobanse.batak.server.deck.BatakException;

public class PlayerRequestProcessor implements RequestProcessor {

	private RequestMessage message;
	
	public PlayerRequestProcessor(RequestMessage message) {
		this.message = message;
	}
	
	@Override
	public ResponseMessage process() throws BatakException {
		try {
			PlayerAction action = RequestProcessorFactory.getProcessor(message);
			return action.execute(message);
		}catch(Exception e) {
			throw new BatakException(e.getMessage(), e);
		}
	}
	
}
