package tr.cobanse.batak.common;

/**
 * @author coban
 * Request message is responsible to send player action to server
 */
public class RequestMessage {
	
	/**
	 * which card a user will discard
	 */
	private Card card = new Card(Symbol.EMPTY, CardType.NONE);
	/**
	 * player name
	 */
	private String playerName;
	
	/**
	 * what kinds of action will be send
	 * 
	 * possible types are CHAT, DISCARD CARD
	 */
	private RequestType requestType;
	
	/**
	 * in which game a user belongs to
	 */
	private String gameId;
	
	/**
	 * plain text message in chat
	 */
	private String message;
	
	
	private int guessPoint;
	
	public RequestMessage(String playerName, RequestType requestType ) {
		this(playerName,requestType,null);
	}
	
	public RequestMessage(String playerName, RequestType requestType, Card card ) {
		this(playerName, requestType,card, null);
	}

	public RequestMessage(String playerName, RequestType requestType, Card card, String gameId ) {
		this.card = card;
		this.playerName = playerName;
		this.requestType = requestType;
		this.gameId = gameId;
	}

	public Card getCard() {
		return card;
	}


	public void setCard(Card card) {
		this.card = card;
	}


	public String getPlayerName() {
		return playerName;
	}


	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}


	public RequestType getRequestType() {
		return requestType;
	}


	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}
	
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	
	public String getGameId() {
		return gameId;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public int getGuessPoint() {
		return guessPoint;
	}
	
	public void setGuessPoint(int guessPoint) {
		this.guessPoint = guessPoint;
	}
	@Override
	public String toString() {
		return "RequestMessage [card=" + card + ", playerName=" + playerName + ", requestType=" + requestType
				+ ", gameId=" + gameId + "]";
	}

	
}
