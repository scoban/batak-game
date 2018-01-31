package tr.cobanse.batak.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Client implements Runnable {
	
	Socket clientSocket;
	BufferedReader  in;
	OutputStream out;
	
	public Client(Socket socket) throws IOException {
		clientSocket = socket; 
		in = new BufferedReader (new InputStreamReader(socket.getInputStream()));
		out = socket.getOutputStream(); 
	}
	
	@Override
	public void run() {
		try{
			while(true) {
				System.out.print("Enter a message:");
				String line = in.readLine();
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
