package tr.cobanse.batak.server;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.server.action.factory.RequestProcessor;
import tr.cobanse.batak.server.action.factory.RequestProcessorImpl;

public class GameMessageProcessor {
	
	private static final Logger logger = LoggerFactory.getLogger(GameMessageProcessor.class);

	private static GameMessageProcessor gameMessageProcessor = new GameMessageProcessor();
	
	private RequestProcessor processor = new RequestProcessorImpl();
	
	private GameMessageDispatcher gameMessageDispatcher = new GameMessageDispatcherImpl();
	
	private GameContext gameContext = new GameContext();
	
	public static GameMessageProcessor getInstance() {
		return gameMessageProcessor;
	}
	
	public ResponseMessage processMessage(RequestMessage requestMessage, Client client) {
		ResponseMessage responseMessage = processor.process(requestMessage, gameContext);
		if(StringUtils.isBlank(requestMessage.getGameId()) == false && responseMessage.isJoinGame()) {
			gameMessageDispatcher.addSubscriber(requestMessage.getGameId(), client); 
		}
		gameMessageDispatcher.sendMessageToAllPlayers(responseMessage.getCardGame(), responseMessage);
		return responseMessage;
	}
}
