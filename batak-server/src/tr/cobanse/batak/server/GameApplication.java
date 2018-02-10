package tr.cobanse.batak.application;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import tr.cobanse.batak.server.Client;
import tr.cobanse.batak.server.GameRoom;

public class GameApplication extends Thread{

	private GameRoom gameRoom;
	
	private ServerSocket serverSocket;
	
	private int portNumber = 60001;
	
	private volatile boolean running;
	
	public GameApplication(int portNumber) throws IOException {
		this.portNumber = portNumber;
		gameRoom = new GameRoom();
		serverSocket = new ServerSocket(this.portNumber);
	}
	
	/**
	 * listen incoming connection request<br/>
	 * The client connection is added to game room
	 * */
	@Override
	public void run() {
		System.out.println("Game application has started...");
		while(running) {
			System.out.println("Game application is alive...");
			Socket socket;
			try {
				socket = serverSocket.accept();
				System.out.println("a connection request has been received..."+socket.getInetAddress());
				Client client = new Client(socket,null);
				client.sendMessage("Welcome to the batak game...");
				gameRoom.execute(client); 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public synchronized void start() {
		running = true;
		super.start();
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
