package tr.cobanse.batak.common;

import java.util.LinkedList;
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
	private List<Player> players;
	
	private Player currentPlayer;
	
	/**
	 * cards that are discarded by user. This is maximum 4 cards in one turn.
	 * In each turn, it will be cleared.
	 */
	private List<GameRound> cardsInPool = new LinkedList<>();
	
	private String chatMessage;
	
	public ResponseMessage(ResponseType responseType) {
		this.responseType = responseType;
	}
	
	public ResponseMessage(ResponseType responseType, List<String> games, List<Card> cards, String cardGame, List<Player> users) {
		this.availableGames = games;
		this.availableCards = cards;
		this.cardGame = cardGame;
		this.players = users;
		this.responseType = responseType;
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

	public String getCardGame() {
		return cardGame;
	}
	
	public void setCardGame(String cardGame) {
		this.cardGame = cardGame;
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public void setPlayers(List<Player> players) {
		this.players = players;
	}
	
	public ResponseType getResponseType() {
		return responseType;
	}
	
	public void setResponseType(ResponseType responseType) {
		this.responseType = responseType;
	}
	
	public List<GameRound> getCardsInPool() {
		return cardsInPool;
	}
	
	public void setCardsInPool(List<GameRound> cardsInPool) {
		this.cardsInPool = cardsInPool;
	}
	
	public String getChatMessage() {
		return chatMessage;
	}
	
	public void setChatMessage(String chatMessage) {
		this.chatMessage = chatMessage;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	@Override
	public String toString() {
		return "ResponseMessage [responseType=" + responseType + ", availableGames=" + availableGames
				+ ", availableCards=" + availableCards + ", cardGame=" + cardGame + ", players=" + players
				+ ", currentPlayer=" + currentPlayer + ", cardsInPool=" + cardsInPool + ", chatMessage=" + chatMessage
				+ "]";
	}

	public boolean isJoinGame() {
		return responseType == ResponseType.JOIN;
	}
}
