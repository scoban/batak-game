package tr.cobanse.batak.common;

public class Card {
	private Symbol symbol;
	private CardType cardType;
	
	public Card(Symbol symbol, CardType cardType) {
		this.symbol = symbol;
		this.cardType = cardType;
	}
	
	public CardType getCardType() {
		return cardType;
	}
	
	public Symbol getSymbol() {
		return symbol;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) 
			return true;
		
		if(obj==null)
			return false;
		
		if(!(obj instanceof Card))
			return false;
		
		Card other = (Card) obj;
		
		if (cardType != other.cardType)
			return false;
		
		if (symbol != other.symbol)
			return false;
		
		return true;
	}
}
