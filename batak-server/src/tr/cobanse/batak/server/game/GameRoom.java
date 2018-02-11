package tr.cobanse.batak.server.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import tr.cobanse.batak.common.CardGame;
import tr.cobanse.batak.common.Player;

/**
 * @author coban
 * game room stores available games 
 */
public class GameRoom {
	
	private List<CardGame> availableGames;
	
	private static GameRoom gameRoom;
	
	private GameRoom() {
		availableGames = new ArrayList<>();
		initializeGame(2);
	}
	
	private void initializeGame(int nOfGame) {
		IntStream.range(0, nOfGame).forEach(i->availableGames.add(new BatakGame()));
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
	
	public void registerPlayer(Player player, String gameId) {
		CardGame cardGame = availableGames.stream().filter((game)->game.getGameId().equals(gameId))
			.findFirst().orElseThrow(IllegalStateException::new);
		cardGame.addPlayer(player);
	}
	
	public CardGame getCardGame(String gameId) {
		CardGame cardGame = availableGames.stream().filter((game)->game.getGameId().equals(gameId))
			.findFirst().orElseThrow(IllegalStateException::new);
		return cardGame;
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
