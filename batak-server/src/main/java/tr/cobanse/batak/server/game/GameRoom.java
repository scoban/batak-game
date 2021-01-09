package tr.cobanse.batak.server.game;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.cobanse.batak.common.Card;
import tr.cobanse.batak.common.CardGame;
import tr.cobanse.batak.common.Player;
import tr.cobanse.batak.server.util.GameUtil;

/**
 * @author coban
 */
public class GameRoom {
	private static Logger logger = LoggerFactory.getLogger(GameRoom.class);
	private CardGame game;
	private String gameId;
	private int nOfPlayers = 0;
	
	public GameRoom(String gameId) {
		this.game = new BatakGame();
		this.gameId = gameId;
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
 
	public void drawCard(String playerName, Card card) {
		Optional<Player> player = getPlayer(playerName);
		player.ifPresent(p->p.discardCard(card));  
	}

	private Optional<Player> getPlayer(String playerName) {
		return game.getPlayers().stream().filter(p->p.getPlayerName().equals(playerName)).findFirst();
	}

	public List<Card> getPlayerCards(String playerName) {
		Optional<Player> player = getPlayer(playerName);
		if(player.isPresent())
			return player.get().getCards();
		return Collections.emptyList();
	}
}
