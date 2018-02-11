package tr.cobanse.batak.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author coban
 * ResponseMessage class is transferable object which is constructed regarding user request.
 */
public class ResponseMessage {
	
	/**
	 * action type returned regarding user request
	 */
	private ResponseType responseType;
	
	/**
	 * message to be sent to a user
	 */
	private String message = "Welcome";
	
	/**
	 * available games 
	 */
	private List<String> availableGames;
	
	/**
	 * user hands card
	 */
	private List<Card> availableCards;
	
	/**
	 * user game info
	 */
	private String cardGame;
	
	/**
	 * the user in a game 
	 */
	private List<String> users;
	
	/**
	 * cards that are discarded by user. This is maximum 4 cards in one turn.
	 * In each turn, it will be cleared.
	 */
	private List<Card> cardsInPool = new ArrayList<Card>();
	
	public ResponseMessage(String message, List<String> games) {
		this(message, games, new ArrayList<>());
	}
	
	public ResponseMessage(String message, List<String> games, List<Card> cards) {
		this(message, games, cards, new NullGame().getGameId());
	}
	
	public ResponseMessage(String message, List<String> games, List<Card> cards, String cardGame) {
		this(message, games, cards, cardGame, new ArrayList<>());
	}
	
	public ResponseMessage(String message, List<String> games, List<Card> cards, String cardGame, List<String> users) {
		this(message, games, cards, cardGame, users, ResponseType.GREETING);
	}
	
	public ResponseMessage(String message, List<String> games, List<Card> cards, String cardGame, List<String> users, ResponseType responseType) {
		this.message = message;
		this.availableGames = games;
		this.availableCards = cards;
		this.cardGame = cardGame;
		this.users = users;
		this.responseType = responseType;
	}
	
	public String getMessage() {
		return message;
	}

	public List<String> getAvailableGames() {
		return availableGames;
	}
	
	public void setAvailableGames(List<String> availableGames) {
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
	
	public String getCardGame() {
		return cardGame;
	}
	
	public void setCardGame(String cardGame) {
		this.cardGame = cardGame;
	}
	
	public List<String> getUsers() {
		return users;
	}
	
	public void setUsers(List<String> users) {
		this.users = users;
	}
	
	public ResponseType getResponseType() {
		return responseType;
	}
	
	public void setResponseType(ResponseType responseType) {
		this.responseType = responseType;
	}
	
	public List<Card> getCardsInPool() {
		return cardsInPool;
	}
	
	public void setCardsInPool(List<Card> cardsInPool) {
		this.cardsInPool = cardsInPool;
	}
	
}
