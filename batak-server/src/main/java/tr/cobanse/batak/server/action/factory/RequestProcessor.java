package tr.cobanse.batak.server.action.factory;

import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.server.GameContext;

public interface RequestProcessor {

	public ResponseMessage process(RequestMessage message, GameContext gameContext);

}
