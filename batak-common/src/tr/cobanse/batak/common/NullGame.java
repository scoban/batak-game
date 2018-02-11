package tr.cobanse.batak.common;

import java.util.List;

public class NullGame implements CardGame{

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

	@Override
	public ResponseMessage execute(RequestMessage message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	public String getGameId() {
		return gameId;
	}

}
