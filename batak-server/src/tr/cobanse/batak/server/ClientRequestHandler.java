package tr.cobanse.batak.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientRequestHandler {
	private ExecutorService executorService;
	
	public ClientRequestHandler() {
		executorService = Executors.newSingleThreadExecutor();
	}

	
}
