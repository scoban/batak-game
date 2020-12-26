package tr.cobanse.batak.server.util;

import org.apache.commons.lang3.StringUtils;

import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.server.deck.BatakException;

public class RequestCommandValidator {

	
	public void validateRequest(RequestMessage requestMessage) {
		String gameToBeJoined = requestMessage.getGameId();
		if(StringUtils.isBlank(gameToBeJoined)) {
			throw new BatakException(GameExceptionMessage.INVALID_GAME_ROOM);
		}
	}
}
