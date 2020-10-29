package tr.cobanse.batak.server.action;

import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.ResponseMessage;

public interface PlayerAction {
	public ResponseMessage execute(RequestMessage requestMessage);
}
