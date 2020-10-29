package tr.cobanse.batak.server.deck;

import java.util.Collections;
import java.util.List;

import tr.cobanse.batak.common.Card;
import tr.cobanse.batak.common.CardType;
import tr.cobanse.batak.common.Symbol;

public class Deck {
	private static Deck deck;
	
	private List<Card> cards;
	
	public static Deck getInstance() {
		if(deck==null)
			deck = new Deck();
		return deck;
	}
	
	private Deck() {
		initializeDeck();
		shuffle();
	}

	private void shuffle() {
		Collections.shuffle(cards); 
	}

	private void initializeDeck() {
		List<Symbol> symbols = Symbol.asList();
		List<CardType> cardTypes = CardType.asList();
			
		for (CardType cardType : cardTypes)
			for (Symbol symbol : symbols) 
				cards.add(new Card(symbol, cardType));
	}
	
	public Card getCard() throws BatakException { 
		if(cards.size()==0)
			throw new BatakException("There is no card available");
	
		return cards.get(0);
	}
}
