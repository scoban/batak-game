package tr.cobanse.batak.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Player {
	
	/**
	 * stores user cards
	 */
	private List<Card> cards = new ArrayList<>();
	
	/**
	 * player name
	 */
	private String playerName;
	
	protected Player(String playerName) {
		super();
		this.playerName = playerName;
	}

	/**
	 * Test whether a user contains card
	 * @param card
	 * @return
	 */
	public boolean containsCard(Card card) {
		return cards.contains(card);
	}
	
	/**
	 * give a card to user
	 * @param card
	 */
	public void drawCard(Card card) {
		if(cards.contains(card))
			return;
		
		cards.add(card);
	}
	
	/**
	 * removes card from user
	 * @param card
	 */
	public void discardCard(Card card) {
		cards.remove(card);
	}
	
	public String getPlayerName() {
		return playerName;
	}
	
	public int cardSize() {
		return cards.size();
	}
	
	public List<Card> getCards() {
		return Collections.unmodifiableList(cards);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((playerName == null) ? 0 : playerName.hashCode());
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
		Player other = (Player) obj;
		if (playerName == null) {
			if (other.playerName != null)
				return false;
		} 
		else if (!playerName.equals(other.playerName))
			return false;
		return true;
	}
}
