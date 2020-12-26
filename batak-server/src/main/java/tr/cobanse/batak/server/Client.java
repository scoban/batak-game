package tr.cobanse.batak.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Collections;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.common.ResponseType;

/**
 * @author selamic
 * clients keeps connection open during the game
 */
public class Client implements Runnable {
	
	private Logger logger = LoggerFactory.getLogger(Client.class);
	private Socket clientSocket;
	private BufferedReader in = null;
	private PrintWriter out = null;
	private Gson gson;
	
	public Client(Socket socket) throws IOException  {
		clientSocket = socket; 
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		out = new PrintWriter(clientSocket.getOutputStream());
		gson = new Gson();
	}
	
	public void startListening() {
		Thread t = new Thread(this);
		t.start();
	}
	
	@Override
	public void run() {
		try{
			logger.debug("Client is created and listening...");
			while(clientSocket.isClosed() == false) {
				String request = in.readLine();
				if(StringUtils.isBlank(request))
					break;
				logger.debug("receiving action {}" , request);
				processRequest(request);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		} finally {
			try {
				releaseResource();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}			
		}
	}
	
	private void processRequest(String rawSocketMessage) throws IOException {
		logger.debug("received message {}", rawSocketMessage);
		try {
			RequestMessage requestMessage = gson.fromJson(rawSocketMessage, RequestMessage.class);
			GameMessageProcessor.getInstance().processMessage(requestMessage, this);
//			ResponseMessage responseMessage = GameMessageProcessor.getInstance().processMessage(requestMessage, this);
//			sendMessage(responseMessage);				
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			sendMessage(new ResponseMessage("ERROR", Collections.emptyList(), Collections.emptyList(), null, Collections.emptyList(), ResponseType.ERROR));
		}
	}

	private void releaseResource() throws IOException {
		if(in != null) {
			in.close();
		}
		if(out!=null) {
			out.close();
		}
		if(clientSocket!=null) {
			clientSocket.close();
		}
	}

	public boolean healthStatus() {
		return false;
	}

	public void sendMessage(ResponseMessage message) throws IOException {
		String msg = gson.toJson(message);
		logger.debug("sending message to client {}", msg);
		out.println(msg); 
		out.flush();
	}
}
