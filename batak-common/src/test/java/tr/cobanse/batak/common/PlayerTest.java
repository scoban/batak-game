package tr.cobanse.batak.common;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class PlayerTest {
	
	@Test
	public void testDrawCard() {
		Card card = new Card(Symbol.SINEK, CardType.AS);
		Player player = new Player("selami") {};
		player.drawCard(card);
		assertTrue(player.cardSize() == 1, "can not draw card");
	}
	
	@Test
	public void testDrawCardDuplicate() {
		Card card = new Card(Symbol.SINEK, CardType.AS);
		Player player = new Player("selami") {};
		player.drawCard(card);
		player.drawCard(card);
		assertTrue(player.cardSize() == 1, "can not draw card");
	}
	
	@Test
	public void testDiscardCard() {
		Card card = new Card(Symbol.SINEK, CardType.AS);
		Player player = new Player("selami") {};
		player.drawCard(card);
		player.discardCard(card); 
		assertTrue(player.cardSize() == 0, "can not draw card");
	}
}
