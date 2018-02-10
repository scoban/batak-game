package tr.cobanse.batak.common;

import java.util.List;

/**
 * @author coban
 * ResponseMessage class is transferable object which is constructed regarding user request.
 */
public class ResponseMessage {
	
	private String message = "Welcome";
	
	/**
	 * available games 
	 */
	private List<CardGame> availableGames;
	
	/**
	 * user hands card
	 */
	private List<Card> availableCards;
	
	/**
	 * user game info
	 */
	private CardGame cardGame;
	
	private List<String> users;
	
	public ResponseMessage(String message, List<CardGame> games) {
		this(message, games, null, null);
	}
	
	public ResponseMessage(String message, List<CardGame> games, List<Card> cards) {
		this(message, games, cards, null);
	}
	
	public ResponseMessage(String message, List<CardGame> games, List<Card> cards, CardGame cardGame) {
		this(message, games, cards, cardGame, null);
	}
	
	public ResponseMessage(String message, List<CardGame> games, List<Card> cards, CardGame cardGame, List<String> users) {
		this.message = message;
		this.availableGames = games;
		this.availableCards = cards;
		this.cardGame = cardGame;
		this.users = users;
	}
	
	public String getMessage() {
		return message;
	}

	public List<CardGame> getAvailableGames() {
		return availableGames;
	}

	public void setAvailableGames(List<CardGame> availableGames) {
		this.availableGames = availableGames;
	}

	public List<Card> getAvailableCards() {
		return availableCards;
	}

	public void setAvailableCards(List<Card> availableCards) {
		this.availableCards = availableCards;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public CardGame getCardGame() {
		return cardGame;
	}
	
	public void setCardGame(CardGame cardGame) {
		this.cardGame = cardGame;
	}
	
	public List<String> getUsers() {
		return users;
	}
	
	public void setUsers(List<String> users) {
		this.users = users;
	}
}
