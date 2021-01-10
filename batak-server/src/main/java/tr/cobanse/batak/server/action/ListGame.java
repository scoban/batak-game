package tr.cobanse.batak.server.action;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.common.ResponseType;
import tr.cobanse.batak.server.GameContext;
import tr.cobanse.batak.server.game.GameRoom;
import tr.cobanse.batak.server.util.JsonUtil;

public class ListGame implements RequestCommand {

	private Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	@Override
	public ResponseMessage execute(RequestMessage requestMessage, GameContext gameContext) {
		logger.debug("executing action {}" , JsonUtil.toJson(requestMessage)); 
		List<GameRoom> availableGames = gameContext.listGames();
		List<String> gameIds = availableGames.stream().map(GameRoom::getGameId).collect(Collectors.toList());
		ResponseMessage responseMessage = new ResponseMessage(ResponseType.LISTGAME, gameIds, null, null, null); 
		logger.debug("action executed. returning message is {} " , responseMessage);
		return responseMessage;
	}

}
