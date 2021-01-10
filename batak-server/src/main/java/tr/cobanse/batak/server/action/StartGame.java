package tr.cobanse.batak.server.action;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.cobanse.batak.common.Player;
import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.common.ResponseType;
import tr.cobanse.batak.server.GameContext;
import tr.cobanse.batak.server.deck.BatakException;
import tr.cobanse.batak.server.game.GameRoom;
import tr.cobanse.batak.server.game.controller.CardDistributeService;
import tr.cobanse.batak.server.util.GameExceptionMessage;
import tr.cobanse.batak.server.util.JsonUtil;
import tr.cobanse.batak.server.util.RequestCommandValidator;

public class StartGame implements RequestCommand{

	private Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	private RequestCommandValidator requestValidator;
	private CardDistributeService cardDistributeService;
	
	public StartGame(RequestCommandValidator requestValidator, CardDistributeService cardDistributeService) {
		this.requestValidator = requestValidator;
		this.cardDistributeService = cardDistributeService;
	}
	@Override
	public ResponseMessage execute(RequestMessage requestMessage, GameContext gameContext) {
		logger.info("user sent ready request command {}", JsonUtil.toJson(requestMessage)); 
		requestValidator.validateRequest(requestMessage);
		GameRoom gameRoom = gameContext.findGame(requestMessage.getGameId()).orElseThrow(()->new BatakException(GameExceptionMessage.INVALID_GAME_ROOM));
		Player player = gameRoom.getGame().getPlayers().stream().filter(p->p.getPlayerName().equalsIgnoreCase(requestMessage.getPlayerName()))
				.findFirst().orElseThrow(() -> new BatakException(GameExceptionMessage.USER_NOT_FOUND));
		player.setReady(true);
		distributeCards(gameRoom);
		return new ResponseMessage(ResponseType.STARTGAME, Arrays.asList(gameRoom.getGameId()), player.getCards(), gameRoom.getGameId(), gameRoom.getGame().getPlayers());
	}
	private void distributeCards(GameRoom gameRoom) {
		Player waitingPlayer = gameRoom.getGame().getPlayers().stream().filter(p->p.isReady() == false).findAny().orElse(null);
		if(waitingPlayer != null) {
			return;
		}
		cardDistributeService.distributeCards(gameRoom); 
	} 
}
