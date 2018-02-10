package tr.cobanse.batak.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class GameApplication extends Thread{
	
	private Logger logger = Logger.getLogger(GameApplication.class);

	private ServerSocket serverSocket;
	
	private int portNumber = 60001;
	
	private volatile boolean running;
	
	public GameApplication(int portNumber) throws IOException {
		BasicConfigurator.configure();
		this.portNumber = portNumber;
		serverSocket = new ServerSocket(this.portNumber);
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
				logger.debug("a connection request has been received..."+socket.getInetAddress());
				Client client = new Client(socket,null);
				client.startListening(); 
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
		GameApplication application = new GameApplication(60001);
		application.start();
	}
}
