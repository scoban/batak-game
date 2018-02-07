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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardType == null) ? 0 : cardType.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (cardType != other.cardType)
			return false;
		if (symbol != other.symbol)
			return false;
		return true;
	}
}
