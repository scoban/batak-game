package tr.cobanse.batak.server.action;

import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.server.GameContext;
import tr.cobanse.batak.server.deck.BatakException;

public class NullType implements RequestCommand{

	@Override
	public ResponseMessage execute(RequestMessage requestMessage, GameContext gameContext) {
		throw new BatakException("Invalid command"); 
	}

}
