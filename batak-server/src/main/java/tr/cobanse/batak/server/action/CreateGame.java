package tr.cobanse.batak.server.action;

import java.util.Arrays;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.common.ResponseType;
import tr.cobanse.batak.server.GameContext;
import tr.cobanse.batak.server.game.GameRoom;
import tr.cobanse.batak.server.util.JsonUtil;

/**
 * @author coban
 * This action is constructed upon user request type CRATEGAME
 * Once a user request to create a new game, it
 */
public class CreateGame implements RequestCommand {

	private Logger logger = LoggerFactory.getLogger(getClass().getName());
			
	@Override
	public ResponseMessage execute(RequestMessage requestMessage, GameContext gameContext) {
		logger.info("new game is created {}" , JsonUtil.toJson(requestMessage)); 
		String gameId = UUID.randomUUID().toString();
		GameRoom gameRoom = new GameRoom(gameId);
		gameContext.addGameRoom(gameRoom);
		return new ResponseMessage(ResponseType.CREATEGAME, Arrays.asList(gameId), null, null, null);
	}

}
