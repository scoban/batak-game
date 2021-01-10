package tr.cobanse.batak.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PlayerTest {
	
	@Test
	void testDrawCard() {
		Card card = new Card(Symbol.SINEK, CardType.AS);
		Player player = new Player("selami") {};
		player.drawCard(card);
		assertEquals(1, player.cardSize(), "can not draw card");
	}
	
	@Test
	void testDrawCardDuplicate() {
		Card card = new Card(Symbol.SINEK, CardType.AS);
		Player player = new Player("selami") {};
		player.drawCard(card);
		player.drawCard(card);
		assertEquals(1, player.cardSize(), "can not draw card");
	}
	
	@Test
	void testDiscardCard() {
		Card card = new Card(Symbol.SINEK, CardType.AS);
		Player player = new Player("selami") {};
		player.drawCard(card);
		player.discardCard(card); 
		assertEquals(1, player.cardSize(), "can not draw card");
	}
}
