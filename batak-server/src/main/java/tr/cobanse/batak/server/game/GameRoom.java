package tr.cobanse.batak.server.game;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.cobanse.batak.common.CardGame;
import tr.cobanse.batak.common.Player;
import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.server.action.PlayerAction;
import tr.cobanse.batak.server.deck.BatakException;

/**
 * @author coban
 */
public class GameRoom {
	private Logger logger = LoggerFactory.getLogger(GameRoom.class);
	private CardGame game;
	private Player[] players = new Player[4];
	private String gameId;
	private int nOfPlayers = 0;
	
	public GameRoom() {
		game = new BatakGame();
		gameId = UUID.randomUUID().toString();
	}
	
	public void registerPlayer(Player player) throws BatakException {
		synchronized (players) {
			logger.info("adding {} th player",  nOfPlayers);
			if(nOfPlayers==4) {
				throw new BatakException("max reached");
			}
			players[nOfPlayers] = player;
			nOfPlayers++;
		}
	}
	
	public void drawPlayer(Player player) throws BatakException {
		isPlayerRegistered(player);
	}
	 
	private void isPlayerRegistered(Player player) throws BatakException{
		throw new BatakException("player is not registered");
	}

	public boolean accept(RequestMessage message) throws BatakException {
		return false;
	}

	private void checkActionIsValid(PlayerAction action) throws BatakException{
		throw new BatakException("invalid request");
	}
	
	public CardGame getGame() {
		return game;
	}
	
	public String getGameId() {
		return gameId;
	}
	
	public int getnOfPlayers() {
		return nOfPlayers;
	}
}
