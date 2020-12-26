package tr.cobanse.batak.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import tr.cobanse.batak.common.Player;
import tr.cobanse.batak.server.deck.BatakException;
import tr.cobanse.batak.server.game.BotPlayer;
import tr.cobanse.batak.server.game.GameRoom;

public class GameTest {

	@Test
	public void testGameRoom_create() {
		GameRoom room = new GameRoom(UUID.randomUUID().toString());
		System.out.printf("Game id:%s\n", room.getGameId());
		assertNotNull(room);
	}
	
	@Test
	public void testGameRoom_join_create() {
		GameRoom room = new GameRoom(UUID.randomUUID().toString());
		RegisterPlayer registerPlayer1 = new RegisterPlayer(room);
		RegisterPlayer registerPlayer2 = new RegisterPlayer(room);
		RegisterPlayer registerPlayer3 = new RegisterPlayer(room);
		RegisterPlayer registerPlayer4 = new RegisterPlayer(room);
		RegisterPlayer registerPlayer5 = new RegisterPlayer(room);
		
		Thread thread1 = new Thread(registerPlayer1);
		thread1.start();
		
		Thread thread2 = new Thread(registerPlayer2);
		thread2.start();
		
		Thread thread3 = new Thread(registerPlayer3);
		thread3.start();
		
		Thread thread4 = new Thread(registerPlayer4);
		thread4.start();
		
		Thread thread5 = new Thread(registerPlayer5);
		thread5.start();
		
		assertEquals(room.getnOfPlayers(), 4);
	}

	class RegisterPlayer implements Runnable {
		private GameRoom room;
		
		public RegisterPlayer(GameRoom room) {
			this.room = room;
		}
		@Override
		public void run() {
			System.out.printf("%s\n",Thread.currentThread().getName());
			Player player = new BotPlayer(Thread.currentThread().getName());
			try {
				room.registerPlayer(player );
			} catch (BatakException e) {
				System.out.println(e.getMessage());
			}
		}
		
	}
}
