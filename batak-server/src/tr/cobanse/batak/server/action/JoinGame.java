package tr.cobanse.batak.server.action;

import tr.cobanse.batak.common.ResponseMessage;

public class JoinGame implements PlayerAction{

	@Override
	public ResponseMessage execute() {
		ResponseMessage message = new ResponseMessage("", null);
		return message;
	}

}
