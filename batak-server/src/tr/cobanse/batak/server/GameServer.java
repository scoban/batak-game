package tr.cobanse.batak.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import tr.cobanse.batak.server.game.GameRoom;

public class GameServer extends Thread {
	
	private Logger logger = LoggerFactory.getLogger(GameServer.class);
	private ServerSocket serverSocket;
	private static int portNumber = 60001;
	private volatile boolean running;
	private Gson gson = new Gson();
	private Map<String, GameRoom> availableGameRoom = new HashMap<String, GameRoom>();
	private static GameServer gameServer;
	private GameServer() throws IOException {
		serverSocket = new ServerSocket(portNumber);
		initGames();
	}
	
	public static GameServer getInstance() throws IOException {
		if(gameServer == null) {
			gameServer = new GameServer();
		}
		return gameServer;
	}
	
	private void initGames() {
		GameRoom gameRoom = new GameRoom();
		availableGameRoom.put(gameRoom.getGameId(), gameRoom);
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
				Client client = new Client(socket);
				Thread clientThread = new Thread(client);
				clientThread.start();
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

	public Map<String, GameRoom> getAvailableGameRoom() {
		return availableGameRoom;
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
		GameServer application = GameServer.getInstance();
		application.start();
	}
}
