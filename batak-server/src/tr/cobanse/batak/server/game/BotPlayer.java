package tr.cobanse.batak.server.game;

import java.util.UUID;

import tr.cobanse.batak.common.Player;

public class BotPlayer extends Player{

	public BotPlayer(String playerName) {
		super(UUID.randomUUID().toString());
	}

}
