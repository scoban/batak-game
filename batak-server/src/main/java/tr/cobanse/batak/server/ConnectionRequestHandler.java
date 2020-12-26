package tr.cobanse.batak.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.cobanse.batak.server.socket.SocketConnectionHandler;
import tr.cobanse.batak.server.socket.SocketConnectionHandlerImpl;

/**
 * @author selamic
 * responsible for accepting connection request
 */
public class ConnectionRequestHandler extends Thread {
	
	private Logger logger = LoggerFactory.getLogger(ConnectionRequestHandler.class);
	
	/**
	 * listening socket connections
	 */
	private ServerSocket serverSocket;
	
	/**
	 * accepts connections on port
	 */
	private int portNumber = 60001;
	
	/**
	 * is server running?
	 */
	private volatile boolean running;
	
	/**
	 * responsible for accepting connections. additional constraints, banned , ip check etc operation should be performed here
	 */
	private SocketConnectionHandler socketConnectionProcessor = new SocketConnectionHandlerImpl();
	
	private ConnectionRequestHandler() throws IOException {
		serverSocket = new ServerSocket(portNumber);
	}
	
	/**
	 * listen incoming connection request<br/>
	 * The client connection is added to game room
	 * */
	@Override
	public void run() {
		logger.debug("Game application has started...");
		while(running) {
			logger.debug("Game application is alive...");
			Socket socket;
			try {
				socket = serverSocket.accept();
				logger.debug("a connection request has been received {}", socket.getInetAddress());
				socketConnectionProcessor.accept(socket);
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		if(serverSocket!=null) {
			try {
				serverSocket.close(); 
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
	}

	@Override
	public synchronized void start() {
		running = true;
		super.start();
	}
	
	public void stopGame() {
		running = false;
	}
	
	public int getPortNumber() {
		return portNumber;
	}
	public static void main(String[] args) throws IOException { 
		//game application listens connection request
		ConnectionRequestHandler application = new ConnectionRequestHandler();
		application.start();
	}

	public void shutDown() {
		logger.info("shutting down game server...");
		}
}
