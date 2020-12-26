package tr.cobanse.batak.server.action;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.cobanse.batak.common.Card;
import tr.cobanse.batak.common.NullGame;
import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.common.ResponseType;
import tr.cobanse.batak.server.GameContext;
import tr.cobanse.batak.server.game.GameRoom;

public class ListGame implements RequestCommand {

	private Logger logger = LoggerFactory.getLogger(ListGame.class);
	
	private static final String MESSAGE = "List of available games";
	
	@Override
	public ResponseMessage execute(RequestMessage requestMessage, GameContext gameContext) {
		logger.debug("executing action {}" , requestMessage);
		List<GameRoom> availableGames = gameContext.listGames();
		List<String> gameIds = availableGames.stream().map(GameRoom::getGameId).collect(Collectors.toList());
		ResponseMessage responseMessage = new ResponseMessage(MESSAGE, gameIds, new ArrayList<Card>(), new NullGame().getGameId(), 
				new ArrayList<String>(), ResponseType.LISTGAME);
		logger.debug("action executed. returning message is {} " , responseMessage);
		return responseMessage;
	}

}
