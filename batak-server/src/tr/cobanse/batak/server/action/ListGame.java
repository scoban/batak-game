package tr.cobanse.batak.server.action;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import tr.cobanse.batak.common.Card;
import tr.cobanse.batak.common.NullGame;
import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.common.ResponseType;
import tr.cobanse.batak.server.game.GameRoom;

public class ListGame implements PlayerAction{

	private final String MESSAGE = "List of available games";
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Override
	public ResponseMessage execute(RequestMessage requestMessage) {
		logger.debug("executing action..." + requestMessage);
		List<String> availableGames = GameRoom.getInstance().getAvailableGames().stream().map((cardGame)->cardGame.getGameId()).collect(Collectors.toList());
		ResponseMessage responseMessage = new ResponseMessage(MESSAGE, availableGames, new ArrayList<Card>(), new NullGame().getGameId(), 
				new ArrayList<String>(), ResponseType.LISTGAME);
		logger.debug("action executed. returning message is " + responseMessage);
		return responseMessage;
	}

}
