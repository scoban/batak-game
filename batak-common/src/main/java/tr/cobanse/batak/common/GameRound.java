package tr.cobanse.batak.common;

public class GameRound {

	private Player player;
	private Card card;
	
	public GameRound(Player player, Card card) {
		this.card = card;
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}
}
