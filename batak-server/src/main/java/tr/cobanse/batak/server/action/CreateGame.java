package tr.cobanse.batak.server.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.ResponseMessage;

/**
 * @author coban
 * This action is constructed upon user request type CRATEGAME
 * Once a user request to create a new game, it
 */
public class CreateGame implements PlayerAction {

	private Logger logger = LoggerFactory.getLogger(getClass().getName());
			
	@Override
	public ResponseMessage execute(RequestMessage requestMessage) {
		throw new UnsupportedOperationException("not yet implemented");
	}

}
