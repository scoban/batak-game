package tr.cobanse.batak.server.action;

import java.util.Collections;

import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.server.GameContext;

public class EchoLoopMessage implements RequestCommand{

	@Override
	public ResponseMessage execute(RequestMessage requestMessage, GameContext gameContext) {
		return new ResponseMessage("Selami", Collections.emptyList());
	}
}
