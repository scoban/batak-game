package tr.cobanse.batak.server;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class GameServerTest {

	private GameServer gameServer;
	
	@Test
	public void startGameServer() throws IOException {
		gameServer = GameServer.getInstance();
		gameServer.start();
		assertTrue(gameServer.isAlive(), "Game server is running");
		gameServer.shutDown();
	}
}
