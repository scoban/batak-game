package tr.cobanse.batak.server.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import tr.cobanse.batak.common.Card;
import tr.cobanse.batak.common.Player;
import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.common.ResponseType;
import tr.cobanse.batak.server.GameContext;
import tr.cobanse.batak.server.game.GameRoom;
import tr.cobanse.batak.server.util.GameExceptionMessage;

public class SendMessage implements RequestCommand{

	private static final String MESSAGE = "CHAT MESSAGE";
	
	@Override
	public ResponseMessage execute(RequestMessage requestMessage, GameContext gameContext) {
		String chatMessage = requestMessage.getMessage();
		String gameToBeJoined = requestMessage.getGameId();
		if(StringUtils.isBlank(gameToBeJoined)) {
			return new ResponseMessage(GameExceptionMessage.INVALID_GAME_ROOM, ResponseType.ERROR);
		}
		List<GameRoom> availableGames = gameContext.listGames();
		GameRoom gameRoom = availableGames.stream().filter(g->g.getGameId().equals(requestMessage.getGameId())).findFirst().orElse(null);
		if(gameRoom == null) {
			return new ResponseMessage(GameExceptionMessage.INVALID_GAME_ROOM, ResponseType.ERROR);
		}
		
		ResponseMessage responseMessage = new ResponseMessage(MESSAGE, Arrays.asList(gameRoom.getGameId()), new ArrayList<Card>(), gameRoom.getGameId(), 
				gameRoom.getPlayers().stream().map(Player::getPlayerName).collect(Collectors.toList()), ResponseType.CHAT);
		responseMessage.setChatMessage(chatMessage);//TODO filter bad words :)
		return responseMessage;
	}

}
