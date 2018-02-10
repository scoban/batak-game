package tr.cobanse.batak.server;

import java.util.List;

import tr.cobanse.batak.action.PlayerAction;
import tr.cobanse.batak.common.Player;

public class BatakGame implements CardGame{

	@Override
	public List<Player> getPlayers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPlayer(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveAction(PlayerAction action) {
		action.act();
	}

	@Override
	public Player currentPlayer() {
		// TODO Auto-generated method stub
		return null;
	}


}
