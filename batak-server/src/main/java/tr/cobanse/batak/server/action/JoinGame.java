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
import tr.cobanse.batak.server.game.HumanPlayer;
import tr.cobanse.batak.server.util.GameExceptionMessage;
import tr.cobanse.batak.server.util.RequestCommandValidator;

public class JoinGame implements RequestCommand{

	private static final String MESSAGE = "JOIN GAME";

	private RequestCommandValidator requestValidator;
	
	public JoinGame(RequestCommandValidator requestValidator) {
		this.requestValidator = requestValidator;
	}
	
	@Override
	public ResponseMessage execute(RequestMessage requestMessage, GameContext gameContext) {
		requestValidator.validateRequest(requestMessage);
		GameRoom gameRoom = gameContext.findGame(requestMessage.getGameId()).orElseThrow(()->new BatakException(GameExceptionMessage.INVALID_GAME_ROOM));		
		if(gameRoom.isFull()) {
			throw new BatakException(GameExceptionMessage.GAME_ROOM_FULL);
		}
		Player player = gameRoom.getGame().getPlayers().stream().filter(p->p.getPlayerName().equalsIgnoreCase(requestMessage.getPlayerName())).findFirst().orElse(null);
		if(player != null) {
			throw new BatakException(GameExceptionMessage.EXISTING_PLAYER_NAME);
		}
		gameRoom.getGame().addPlayer(new HumanPlayer(requestMessage.getPlayerName())); 
		return new ResponseMessage(ResponseType.JOIN, Collections.emptyList(), Collections.emptyList(), gameRoom.getGameId(), gameRoom.getGame().getPlayers().stream().map(p->p.getPlayerName())
				.collect(Collectors.toList())); 
	}

}
