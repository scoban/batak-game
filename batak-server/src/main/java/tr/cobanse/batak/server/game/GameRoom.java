package tr.cobanse.batak.server.game;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.cobanse.batak.common.CardGame;
import tr.cobanse.batak.common.Player;
import tr.cobanse.batak.server.deck.BatakException;
import tr.cobanse.batak.server.util.GameUtil;

/**
 * @author coban
 */
public class GameRoom {
	private static Logger logger = LoggerFactory.getLogger(GameRoom.class);
	private CardGame game;
	private LinkedList<Player> players = new LinkedList<>();
	private String gameId;
	private int nOfPlayers = 0;
	
	public GameRoom(String gameId) {
		this.game = new BatakGame();
		this.gameId = gameId;
	}
	
	public void registerPlayer(Player player) {
		synchronized (players) {
			logger.info("adding {} th player",  nOfPlayers);
			if(nOfPlayers==4) {
				throw new BatakException("max reached");
			}
			players.add(player);
			nOfPlayers++;
		}
	}
	
	public void drawPlayer(String playerName) {
		Player player = players.stream().filter(p->p.getPlayerName().equals(playerName)).findFirst().orElse(null);
		if(player != null) {
			players.remove(player);
			nOfPlayers--;
		}
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

	public boolean isFull() {
		return nOfPlayers == GameUtil.MAX_PLAYER;
	}
 
	public List<Player> getPlayers() {
		return players;
	}
}
