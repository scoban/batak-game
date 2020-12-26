package tr.cobanse.batak.server.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import tr.cobanse.batak.common.Card;
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
		GameRoom gameRoom = gameContext.findGame(requestMessage.getGameId());
		if(gameRoom == null) {
			throw new BatakException(GameExceptionMessage.INVALID_GAME_ROOM);
		}
		
		if(gameRoom.isFull()) {
			throw new BatakException(GameExceptionMessage.GAME_ROOM_FULL);
		}
		Player existingPlayerName = gameRoom.getPlayers().stream().filter(p->p.getPlayerName().equalsIgnoreCase(requestMessage.getPlayerName())).findFirst().orElse(null);
		if(existingPlayerName != null) {
			throw new BatakException(GameExceptionMessage.EXISTING_PLAYER_NAME);
		}
		gameRoom.registerPlayer(new HumanPlayer(requestMessage.getPlayerName())); 
		return new ResponseMessage(MESSAGE, Arrays.asList(gameRoom.getGameId()), new ArrayList<Card>(), gameRoom.getGameId(), 
				gameRoom.getPlayers().stream().map(Player::getPlayerName).collect(Collectors.toList()), ResponseType.JOIN);
	}

}
