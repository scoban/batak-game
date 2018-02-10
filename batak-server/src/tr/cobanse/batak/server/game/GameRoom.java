package tr.cobanse.batak.server.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tr.cobanse.batak.common.CardGame;

/**
 * @author coban
 * game room stores available games 
 */
public class GameRoom {
	
	private List<CardGame> availableGames;
	
	private static GameRoom gameRoom;
	
	private GameRoom() {
		availableGames = new ArrayList<>();
	}
	
	/**
	 * @return
	 */
	public static GameRoom getInstance() {
		if(gameRoom==null)
			gameRoom = new GameRoom();
		return gameRoom;
	}
	
	public void createGame(CardGame newGame) {
		availableGames.add(newGame);
	}
	
	public List<CardGame> getAvailableGames() {
		return Collections.unmodifiableList(availableGames);
	}
	
	public void closeGame(CardGame game) {
		availableGames.remove(game);
	}
	
	public int getNumberOfGame() {
		return availableGames.size();
	}
}
