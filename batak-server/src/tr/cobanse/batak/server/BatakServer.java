package tr.cobanse.batak.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class BatakServer extends Thread{

	private BatakGame batakGame;
	private ServerSocket serverSocket;
	private static int PORT = 60001;
	private List<Client> clients = new ArrayList<Client>();
	private final int size = 4;
	
	public BatakServer() throws IOException { 
		this.serverSocket = new ServerSocket(PORT);
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println("Game is listening incoming clients");
		BatakServer server = new BatakServer();
		server.start();
	}
	
	/**
	 * Listens incoming client game request
	 */
	@Override
	public void run() {
		try {
			while (clients.size()<size) { 
				Socket socket = serverSocket.accept();
				System.out.println("a connection request has been received..."+socket.getInetAddress());
				Client client = new Client(socket);
				clients.add(client);
				Thread t = new Thread(client);
				t.start(); 
				client.sendMessage("Welcome to the batak game...");
			}
			notifyUser("Game will be started in a second");
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	/**
	 * send message to all client
	 * @param message
	 * @throws IOException
	 */
	public void notifyUser(String message) throws IOException{ 
		for (Client client : clients) {
			client.sendMessage(message);
		}
	}
	
	public void notifyGameStatus() {
		for (Client client : clients) {
			client.sendGameSatus(batakGame);
		}
	}
}
