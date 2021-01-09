package tr.cobanse.batak.common;

import java.util.List;

public interface CardGame {

	public String getGameId();
	
	public List<Player> getPlayers();
	
	public void addPlayer(Player player);
	
	public void removePlayer(Player player);
	
	public Player currentPlayer();
	
}
