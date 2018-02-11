package tr.cobanse.batak.server.action;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import tr.cobanse.batak.common.CardGame;
import tr.cobanse.batak.common.Player;
import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.common.ResponseType;
import tr.cobanse.batak.server.game.BatakGame;
import tr.cobanse.batak.server.game.GameRoom;
import tr.cobanse.batak.server.game.HumanPlayer;

/**
 * @author coban
 * This action is constructed upon user request type CRATEGAME
 * Once a user request to create a new game, it
 */
public class CreateGame implements PlayerAction {

	private final String MESSAGE = "Game created...";
	private Logger logger = Logger.getLogger(getClass().getName());
			
	@Override
	public ResponseMessage execute(RequestMessage requestMessage) {
		logger.debug("executing action..." + requestMessage);
		CardGame newGame = new BatakGame();
		Player player = new HumanPlayer(requestMessage.getPlayerName());
		newGame.addPlayer(player);
		GameRoom.getInstance().createGame(newGame);
		List<String> users = newGame.getPlayers().stream().map(Player::getPlayerName).collect(Collectors.toList());
		List<String> availableGames = GameRoom.getInstance().getAvailableGames().stream().map((cardGame)->cardGame.getGameId()).collect(Collectors.toList());
		ResponseMessage responseMessage = new ResponseMessage(MESSAGE, availableGames, null, newGame.getGameId(), users, ResponseType.CREATEGAME);
		logger.debug("action executed. returning message is " + responseMessage);
		return responseMessage;
	}

}
