package tr.cobanse.batak.server.game;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.cobanse.batak.common.Card;
import tr.cobanse.batak.common.CardGame;
import tr.cobanse.batak.common.GameRound;
import tr.cobanse.batak.common.IBatakGame;
import tr.cobanse.batak.common.Player;
import tr.cobanse.batak.common.Symbol;
import tr.cobanse.batak.server.deck.BatakException;

public class BatakGame implements CardGame, IBatakGame {

	private static final int LAST_PLAYER_INDEX = 3;
 
	private static Logger logger = LoggerFactory.getLogger(BatakGame.class);
	
	List<Player> players;
	private int nOfPlayers = 0;
	private String gameId;
	private int currentPlayerIndex = 0;
	private int guessWinnerPoint = 0; // if a player claim highest point, then he is allowed to choose trump symbol
	private Symbol trumpSymbol = Symbol.EMPTY; // the user tells which symbol is trump 
	private Player claimer;
	private Deque<List<GameRound>> gameRounds = new LinkedList<List<GameRound>>();
	private List<GameRound> currentRound = new LinkedList<GameRound>();
	public BatakGame() {
		players = new LinkedList<>();
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
	public void updatePlayerInTurn() {
		this.currentPlayerIndex ++;
		if(this.currentPlayerIndex > LAST_PLAYER_INDEX) {
			this.currentPlayerIndex = 0;
		}
	}
	
	@Override
	public Player currentPlayer() {
		return players.get(currentPlayerIndex);  
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
	
	public Player getClaimer() {
		return claimer;
	}
	
	public void setClaimer(Player claimer) {
		this.claimer = claimer;
	}
	
	public Symbol getTrumpSymbol() {
		return trumpSymbol;
	}
	
	public void setTrumpSymbol(Symbol trumpSymbol) {
		this.trumpSymbol = trumpSymbol;
	}
	
	public int getGuessWinnerPoint() {
		return guessWinnerPoint;
	}
	
	public void setGuessWinnerPoint(int guessWinnerPoint) {
		this.guessWinnerPoint = guessWinnerPoint;
	}
	
	public void addGameRound(Card card, Player player) {
		currentRound.add(new GameRound(player, card));
		if(currentRound.size() == 4) {
			gameRounds.add(currentRound);
			currentRound.clear();
		}
	}
	
	@Override
	public List<GameRound> getCurrentGameRound() {
		return currentRound;
	}
}
