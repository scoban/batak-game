package tr.cobanse.batak.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.ResponseMessage;

public class Client implements Runnable {
	
	private Logger logger = LoggerFactory.getLogger(Client.class);
	private Socket clientSocket;
	private Gson gson;
	
	public Client(Socket socket) throws IOException {
		clientSocket = socket; 
		gson = new Gson();
	}
	
	public void startListening() {
		Thread t = new Thread(this);
		t.start();
	}
	
	@Override
	public void run() {
		try(BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream());)
		{
			logger.debug("Client is created and listening...");
			String request = in.readLine();
			logger.debug("receiving action " + request);
			RequestMessage requestMessage = gson.fromJson(request, RequestMessage.class);
			RequestProcessor processor = new PlayerRequestProcessor(requestMessage);
			ResponseMessage message = processor.process();
			sendMessage(message, out);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		try {
			releaseResource();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} 
	}
	
	private void releaseResource() throws IOException {
		if(clientSocket!=null) {
			clientSocket.close();
		}
	}

	public boolean healthStatus() {
		return false;
	}

	public void sendMessage(ResponseMessage message, PrintWriter out) throws IOException {
		String msg = gson.toJson(message);
		logger.debug("sending message to client..." + msg);
		out.println(msg); 
		out.flush();
	}
}
