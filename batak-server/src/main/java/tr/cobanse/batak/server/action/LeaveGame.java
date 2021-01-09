package tr.cobanse.batak.server.action;

import java.util.Collections;
import java.util.stream.Collectors;

import tr.cobanse.batak.common.Player;
import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.common.ResponseType;
import tr.cobanse.batak.server.GameContext;
import tr.cobanse.batak.server.deck.BatakException;
import tr.cobanse.batak.server.game.GameRoom;
import tr.cobanse.batak.server.util.GameExceptionMessage;
import tr.cobanse.batak.server.util.RequestCommandValidator;

public class LeaveGame implements RequestCommand{

	private static final String MESSAGE = "LEAVE GAME";
	
	private RequestCommandValidator requestValidator;
	
	public LeaveGame(RequestCommandValidator requestValidator) {
		this.requestValidator = requestValidator;
	}
	
	@Override
	public ResponseMessage execute(RequestMessage requestMessage, GameContext gameContext) {
		requestValidator.validateRequest(requestMessage);
		GameRoom gameRoom = gameContext.findGame(requestMessage.getGameId()).orElseThrow(()->new BatakException(GameExceptionMessage.INVALID_GAME_ROOM));
		Player player = gameRoom.getGame().getPlayers().stream().filter(p->p.getPlayerName().equalsIgnoreCase(requestMessage.getPlayerName()))
				.findFirst().orElseThrow(() -> new BatakException(GameExceptionMessage.USER_NOT_FOUND));
		gameRoom.getGame().removePlayer(player); 
		return new ResponseMessage(ResponseType.LEAVEGAME, Collections.emptyList(), Collections.emptyList(), gameRoom.getGameId(), gameRoom.getGame().getPlayers().stream().map(p->p.getPlayerName())
				.collect(Collectors.toList())); 
	}
}
