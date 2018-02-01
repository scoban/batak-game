package tr.cobanse.batak.common;

import java.util.List;

public class PlayerPair {
	
	List<Player> players;
	
	private int score;
	
	public PlayerPair() {
	}
	
	public void addScore(int score) {
		this.score += score;
	}
	
	public void reset() {
		this.score = 0;
	}
	
	public int getScore() {
		return score;
	}
}
