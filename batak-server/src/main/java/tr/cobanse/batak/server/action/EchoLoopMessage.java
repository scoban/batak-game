package tr.cobanse.batak.server.action;

import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.common.ResponseType;
import tr.cobanse.batak.server.GameContext;

public class EchoLoopMessage implements RequestCommand{

	@Override
	public ResponseMessage execute(RequestMessage requestMessage, GameContext gameContext) {
		return new ResponseMessage(ResponseType.CHAT);
	}
}
