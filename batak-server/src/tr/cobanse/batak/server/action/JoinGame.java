package tr.cobanse.batak.server.action;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tr.cobanse.batak.common.Card;
import tr.cobanse.batak.common.CardGame;
import tr.cobanse.batak.common.NullGame;
import tr.cobanse.batak.common.Player;
import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.common.ResponseType;
import tr.cobanse.batak.server.game.GameRoom;
import tr.cobanse.batak.server.game.HumanPlayer;

public class JoinGame implements PlayerAction{

	private final String MESSAGE = "Joined game successfully";
	@Override
	public ResponseMessage execute(RequestMessage requestMessage) {
		String gameId = requestMessage.getGameId();
		String playerName = requestMessage.getPlayerName();
		Player player = new HumanPlayer(playerName);
		GameRoom.getInstance().registerPlayer(player, gameId); 
		CardGame cardGame = GameRoom.getInstance().getCardGame(gameId);
		List<String> availableGames = GameRoom.getInstance().getAvailableGames().stream().map((cG)->cG.getGameId()).collect(Collectors.toList());
		List<String> users = cardGame.getPlayers().stream().map(ply->ply.getPlayerName()).collect(Collectors.toList());
		ResponseMessage responseMessage = new ResponseMessage(MESSAGE, availableGames, new ArrayList<Card>(), new NullGame().getGameId(), 
				users, ResponseType.JOIN);
		return responseMessage;
	}

}
