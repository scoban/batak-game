package tr.cobanse.batak.server.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.cobanse.batak.common.CardGame;
import tr.cobanse.batak.common.Player;
import tr.cobanse.batak.server.deck.BatakException;

public class BatakGame implements CardGame {

	private static Logger logger = LoggerFactory.getLogger(BatakGame.class);
	
	List<Player> players;
	private int index = 0;
	private int nOfPlayers = 0;
	private String gameId;
	
	public BatakGame() {
		players = new ArrayList<>();
		gameId = UUID.randomUUID().toString();
	}
	
	@Override
	public List<Player> getPlayers() {
		return Collections.unmodifiableList(players);
	}

	@Override
	public void addPlayer(Player player) {
		synchronized (players) {
			logger.info("adding {} th player",  nOfPlayers);
			if(nOfPlayers==4) {
				throw new BatakException("max reached");
			}
			players.add(player);
			nOfPlayers++;
		}
	}

	@Override
	public Player currentPlayer() {
		return players.get(index); 
	}

	@Override
	public void removePlayer(Player player) {
		synchronized (players) {
			if(player != null) {
				players.remove(player);
				nOfPlayers--;
			}			
		}
	}
	
	@Override
	public String getGameId() {
		return gameId;
	}
}
