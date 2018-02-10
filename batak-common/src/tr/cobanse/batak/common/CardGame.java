package tr.cobanse.batak.server;

import java.util.List;

import tr.cobanse.batak.action.PlayerAction;
import tr.cobanse.batak.common.Player;

public interface CardGame {

	public List<Player> getPlayers();
	
	public void addPlayer(Player player);
	
	public void receiveAction(PlayerAction action);
	
	public Player currentPlayer();
}
