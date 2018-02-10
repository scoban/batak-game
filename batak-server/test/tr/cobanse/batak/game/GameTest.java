package tr.cobanse.batak.game;

import org.junit.Assert;
import org.junit.Test;

import tr.cobanse.batak.common.CardGame;
import tr.cobanse.batak.common.Player;
import tr.cobanse.batak.server.game.BatakGame;
import tr.cobanse.batak.server.game.HumanPlayer;

public class GameTest {

	@Test
	public void testGameJoin() {
		CardGame game = new BatakGame();
		game.addPlayer(new HumanPlayer("selami"));
		Assert.assertEquals("New player cannot be added", 1, game.getPlayers().size());
	}
	
	@Test
	public void testGameLeave() {
		CardGame game = new BatakGame();
		Player player = new HumanPlayer("selami"); 
		game.addPlayer(player); 
		game.removePlayer(player);
		Assert.assertEquals("New player cannot be added", 0, game.getPlayers().size());
	}
	
	
	@Test
	public void testMaxPlayer() {
		CardGame game = new BatakGame();
		game.addPlayer(new HumanPlayer("selami1")); 
		game.addPlayer(new HumanPlayer("selami2")); 
		game.addPlayer(new HumanPlayer("selami3")); 
		game.addPlayer(new HumanPlayer("selami4")); 
		Assert.assertEquals("New player cannot be added", 4, game.getPlayers().size());
	}
	
	@Test(expected=java.lang.IllegalStateException.class)
	public void testMaxPlayerException() {
		CardGame game = new BatakGame();
		game.addPlayer(new HumanPlayer("selami1")); 
		game.addPlayer(new HumanPlayer("selami2")); 
		game.addPlayer(new HumanPlayer("selami3")); 
		game.addPlayer(new HumanPlayer("selami4"));
		game.addPlayer(new HumanPlayer("selami5"));
	}
}
