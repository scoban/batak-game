package tr.cobanse.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class BatakClient implements Runnable{
	BufferedReader console;
	BufferedReader is;
	OutputStream os;
	public BatakClient(Socket socket) throws Exception {
		console = new BufferedReader(new InputStreamReader(System.in));
		is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		os = socket.getOutputStream();
		new Thread(new ServerListener(is)).start();
	}
	
	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("127.0.0.1", 60001);
		BatakClient client = new BatakClient(socket);
		Thread t = new Thread(client);
		t.start();
	}

	@Override
	public void run() {
		try{
			while(true) {
				System.out.print("Enter a message:");
				String line = console.readLine();
				sendMessage(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String message) throws IOException {
		os.write((message + "\r\n").getBytes()); 
		os.flush();
	}
	
}

class ServerListener implements Runnable{
	BufferedReader in;
	public ServerListener(BufferedReader is) {
		this.in = is;
	}
	
	@Override
	public void run() {
		String serverMessage;
		try {
			while(true) {
				serverMessage = in.readLine();
				System.out.println(serverMessage);
			}
		} catch (IOException e) {
			System.out.println("connection closed...");
		}
	}
}