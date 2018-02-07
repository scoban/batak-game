package tr.cobanse.batak.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import tr.cobanse.batak.action.ChangeMate;

public class Client implements Runnable {
	
	CardGame cardGame;
	Socket clientSocket;
	BufferedReader  in;
	OutputStream out;
	
	public Client(Socket socket, CardGame cardGame) throws IOException {
		clientSocket = socket; 
		in = new BufferedReader (new InputStreamReader(socket.getInputStream()));
		out = socket.getOutputStream();
		this.cardGame = cardGame;
	}
	
	@Override
	public void run() {
		try{
			while(true) {
				String line = in.readLine();
				cardGame.receiveAction(new ChangeMate());
				sendMessage(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String message) throws IOException { 
		out.write((message + "\r\n").getBytes()); 
		out.flush();
	}

	/**
	 * send game configuration to the connected clients 
	 * status consists of number of player, current player
	 * @param batakGame
	 */
	public void sendGameSatus(BatakGame batakGame) {
	
	}
}
