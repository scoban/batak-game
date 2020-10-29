package tr.cobanse.batak.server.action;

import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.ResponseMessage;

public class DiscardCard implements PlayerAction{

	@Override
	public ResponseMessage execute(RequestMessage requestMessage) {
		ResponseMessage message = new ResponseMessage("", null);
		return message;
	}

}
