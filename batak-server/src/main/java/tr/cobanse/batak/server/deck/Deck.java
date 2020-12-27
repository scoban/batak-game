package tr.cobanse.batak.server.deck;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import tr.cobanse.batak.common.Card;
import tr.cobanse.batak.common.CardType;
import tr.cobanse.batak.common.Symbol;

public class Deck {
	
	private Stack<Card> cards = new Stack<Card>();
	
	public Deck() {
		initializeDeck();
		shuffle();
	}

	private void shuffle() {
		Collections.shuffle(cards); 
	}

	private void initializeDeck() {
		List<Symbol> symbols = Symbol.asList();
		List<CardType> cardTypes = CardType.asList();
		cardTypes.stream().forEach(cardType-> {
			symbols.stream().forEach(symbol-> {
				cards.push(new Card(symbol, cardType));
			});
		});
	}
	
	public int remainingCardSize() {
		return cards.size();
	}
	
	public Card getCard() throws BatakException { 
		if(remainingCardSize() == 0)
			return null;
	
		return cards.pop();
	}
}
