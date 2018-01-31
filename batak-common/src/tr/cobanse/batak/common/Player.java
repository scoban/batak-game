package tr.cobanse.batak.common;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
	
	/**
	 * stores user cards
	 */
	private List<Card> cards = new ArrayList<Card>();
	
	/**
	 * player name
	 */
	private String playerName;
	
	public Player(String playerName) {
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
}
