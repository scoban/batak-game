package tr.cobanse.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BatakServer{
	
	public BatakServer(Socket socket) throws IOException {
		
	}
	
	public static void main(String[] args) throws IOException {
		try(ServerSocket socket = new ServerSocket(60001)){
			ClientRequestThread clientThread = new ClientRequestThread(socket.accept());
			System.out.println("client connected");
			new Thread(clientThread).start();
		}
	}
}

class ClientRequestThread implements Runnable {
	Socket clientSocket;
	BufferedReader  in;
	OutputStream out;
	
	public ClientRequestThread(Socket socket) throws IOException {
		clientSocket = socket; 
		in = new BufferedReader (new InputStreamReader(socket.getInputStream()));
		out = socket.getOutputStream();
	}

	@Override
	public void run() {
		try {
			sendMessage("Welcome");
			String clientMessage = null;
			while(true) {
				clientMessage = in.readLine();
				System.out.println(clientMessage);
				sendMessage(clientMessage); 
			}
		} catch (IOException e) {
			System.out.println("client connection closed");
		}
	}
	
	public void sendMessage(String message) throws IOException {
		out.write((message + "\r\n").getBytes()); 
		out.flush();
	}
	
}
