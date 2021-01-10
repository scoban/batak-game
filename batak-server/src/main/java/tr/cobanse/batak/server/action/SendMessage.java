package tr.cobanse.batak.server.action;

import org.apache.commons.lang3.StringUtils;

import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.common.ResponseType;
import tr.cobanse.batak.server.GameContext;
import tr.cobanse.batak.server.deck.BatakException;
import tr.cobanse.batak.server.game.GameRoom;
import tr.cobanse.batak.server.util.GameExceptionMessage;

public class SendMessage implements RequestCommand{

	@Override
	public ResponseMessage execute(RequestMessage requestMessage, GameContext gameContext) {
		String chatMessage = requestMessage.getMessage();
		String gameToBeJoined = requestMessage.getGameId();
		if(StringUtils.isBlank(gameToBeJoined)) {
			throw new BatakException(GameExceptionMessage.INVALID_GAME_ROOM);
		}
		GameRoom gameRoom = gameContext.findGame(gameToBeJoined).orElseThrow(()->new BatakException(GameExceptionMessage.INVALID_GAME_ROOM));
		ResponseMessage responseMessage = new ResponseMessage(ResponseType.CHAT, null, null, gameRoom.getGameId(), null);
		responseMessage.setChatMessage(chatMessage);//TODO filter bad words :)
		return responseMessage;
	}

}
