package tr.cobanse.batak.server.action.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.server.GameContext;
import tr.cobanse.batak.server.action.RequestCommand;
import tr.cobanse.batak.server.util.JsonUtil;

public class RequestProcessorImpl implements RequestProcessor {

	private static Logger logger = LoggerFactory.getLogger(RequestProcessorImpl.class);
	
	@Override
	public ResponseMessage process(RequestMessage message, GameContext gameContext) {
		if(logger.isInfoEnabled()) {
			logger.info("processing client message {}", JsonUtil.toJson(message)); 
		}
		RequestCommand action = RequestProcessorFactory.getProcessor(message);
		return action.execute(message, gameContext);
	}
	
}
