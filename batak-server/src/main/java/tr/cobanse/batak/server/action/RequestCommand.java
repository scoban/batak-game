package tr.cobanse.batak.server.action;

import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.server.GameContext;

public interface RequestCommand {

	public ResponseMessage execute(RequestMessage requestMessage, GameContext gameContext);
}
