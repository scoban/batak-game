package tr.cobanse.batak.common;

import java.util.List;

public class NullResponse implements CardGame{

	private String gameId = "nullgameid";
	
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
	public void removePlayer(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Player currentPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getGameId() {
		return gameId;
	}

}
