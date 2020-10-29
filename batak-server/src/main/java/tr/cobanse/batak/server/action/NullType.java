package tr.cobanse.batak.server.action;

import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.ResponseMessage;

public class NullType implements PlayerAction{

	@Override
	public ResponseMessage execute(RequestMessage requestMessage) {
		return new ResponseMessage("No type", null); 
	}

}
