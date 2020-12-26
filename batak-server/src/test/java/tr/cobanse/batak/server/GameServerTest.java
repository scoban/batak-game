package tr.cobanse.batak.server;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class GameServerTest {

	private ConnectionRequestHandler gameServer;
	
	@Test
	public void startGameServer() throws IOException {
		gameServer = ConnectionRequestHandler.getInstance();
		gameServer.start();
		assertTrue(gameServer.isAlive(), "Game server is running");
		gameServer.shutDown();
	}
	
	@Test
	public void startGameServerTestTwoConnection() throws IOException {
		gameServer = ConnectionRequestHandler.getInstance();
		gameServer.start();
		assertTrue(gameServer.isAlive(), "Game server is running");
		
		
	}
}
