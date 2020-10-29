package tr.cobanse.batak.common;

import org.junit.Assert;
import org.junit.Test;


public class PlayerTest {
	
	@Test
	public void testDrawCard() {
		Card card = new Card(Symbol.SINEK, CardType.AS);
		Player player = new Player("selami") {};
		player.drawCard(card);
		Assert.assertTrue("can not draw card", player.cardSize() == 1);
	}
	
	@Test
	public void testDrawCardDuplicate() {
		Card card = new Card(Symbol.SINEK, CardType.AS);
		Player player = new Player("selami") {};
		player.drawCard(card);
		player.drawCard(card);
		Assert.assertTrue("can not draw card", player.cardSize() == 1);
	}
	
	@Test
	public void testDiscardCard() {
		Card card = new Card(Symbol.SINEK, CardType.AS);
		Player player = new Player("selami") {};
		player.drawCard(card);
		player.discardCard(card); 
		Assert.assertTrue("can not draw card", player.cardSize() == 0);
	}
}
