package tr.cobanse.batak.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author coban
 * game room stores connected users
 */
public class GameRoom {
	
	private ExecutorService executorService;
	
	public GameRoom() {
		executorService = Executors.newFixedThreadPool(4);
	}
	
	public void execute(Runnable runnable) {
		executorService.execute(runnable);
	}
	
	public void shutdown() {
		executorService.shutdown();
	}
}
