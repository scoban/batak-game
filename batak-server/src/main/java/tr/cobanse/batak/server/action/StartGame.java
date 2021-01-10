package tr.cobanse.batak.server.action;

import java.util.Collections;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.cobanse.batak.common.Player;
import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.common.ResponseType;
import tr.cobanse.batak.server.GameContext;
import tr.cobanse.batak.server.deck.BatakException;
import tr.cobanse.batak.server.game.GameRoom;
import tr.cobanse.batak.server.util.GameExceptionMessage;
import tr.cobanse.batak.server.util.JsonUtil;
import tr.cobanse.batak.server.util.RequestCommandValidator;

public class StartGame implements RequestCommand{

	private Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	private RequestCommandValidator requestValidator;
	
	public StartGame(RequestCommandValidator requestValidator) {
		this.requestValidator = requestValidator;
	}
	@Override
	public ResponseMessage execute(RequestMessage requestMessage, GameContext gameContext) {
		logger.info("user sent ready request command {}", JsonUtil.toJson(requestMessage)); 
		requestValidator.validateRequest(requestMessage);
		GameRoom gameRoom = gameContext.findGame(requestMessage.getGameId()).orElseThrow(()->new BatakException(GameExceptionMessage.INVALID_GAME_ROOM));
		Player player = gameRoom.getGame().getPlayers().stream().filter(p->p.getPlayerName().equalsIgnoreCase(requestMessage.getPlayerName()))
				.findFirst().orElseThrow(() -> new BatakException(GameExceptionMessage.USER_NOT_FOUND));
		player.setReady(true);
		return new ResponseMessage(ResponseType.JOIN, Collections.emptyList(), Collections.emptyList(), gameRoom.getGameId(), gameRoom.getGame().getPlayers());
	}
}
