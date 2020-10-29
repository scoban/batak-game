package tr.cobanse.batak.server.action;

import java.util.ArrayList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.cobanse.batak.common.Card;
import tr.cobanse.batak.common.NullGame;
import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.common.ResponseType;
import tr.cobanse.batak.server.GameServer;
import tr.cobanse.batak.server.deck.BatakException;
import tr.cobanse.batak.server.game.GameRoom;

public class ListGame implements PlayerAction{

	private final String MESSAGE = "List of available games";
	private Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	@Override
	public ResponseMessage execute(RequestMessage requestMessage) {
		try {
			logger.debug("executing action..." + requestMessage);
			Map<String, GameRoom> availableGames = GameServer.getInstance().getAvailableGameRoom();
			ResponseMessage responseMessage = new ResponseMessage(MESSAGE, new ArrayList<String>(availableGames.keySet()), new ArrayList<Card>(), new NullGame().getGameId(), 
					new ArrayList<String>(), ResponseType.LISTGAME);
			logger.debug("action executed. returning message is {} " , responseMessage);
			return responseMessage;
		}catch(Exception e) {
			throw new BatakException(e.getMessage(), e);
		}
	}

}
