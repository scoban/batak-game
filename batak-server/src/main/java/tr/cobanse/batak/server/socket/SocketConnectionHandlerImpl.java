package tr.cobanse.batak.server.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.cobanse.batak.server.Client;

/**
 * @author selamic
 *
 */
public class SocketConnectionHandlerImpl implements SocketConnectionHandler{

	private static final int MAX_THREAD = 10;
	private ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD);
	
	private static Logger logger = LoggerFactory.getLogger(SocketConnectionHandlerImpl.class);
	
	public void accept(Socket socket) {
		InetAddress inetAddress = socket.getInetAddress();
		String hostAddress = inetAddress.getHostAddress();
		logger.info("connection accepted {}", hostAddress);
		try {
			executorService.execute(new Client(socket));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} 
	}
}
