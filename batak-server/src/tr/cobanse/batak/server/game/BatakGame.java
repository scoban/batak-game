package tr.cobanse.batak.server.game;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import tr.cobanse.batak.common.CardGame;
import tr.cobanse.batak.common.Player;
import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.ResponseMessage;

public class BatakGame implements CardGame {

	List<Player> players;
	private int index = 0;
	private int nOfPlayer = 4;
	private String gameId;
	
	public BatakGame() {
		players = new ArrayList<Player>();
		gameId = UUID.randomUUID().toString();
	}
	
	@Override
	public List<Player> getPlayers() {
		return players;
	}

	@Override
	public void addPlayer(Player player) {
		if(players.size()<nOfPlayer)
			players.add(player);
		else
			throw new IllegalStateException("Maximum player number reached");
	}

	@Override
	public Player currentPlayer() {
		return players.get(index); 
	}

	@Override
	public ResponseMessage execute(RequestMessage message) {
		return null;
	}

	@Override
	public void start() {
	}

	@Override
	public void removePlayer(Player player) {
		players.remove(player);
	}
	
}
