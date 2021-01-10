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
import tr.cobanse.batak.server.deck.BatakException;
import tr.cobanse.batak.server.util.GameExceptionMessage;

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
			//https://stackoverflow.com/questions/10240694/java-socket-api-how-to-tell-if-a-connection-has-been-closed/10241044#10241044
			//TODO isClosed and isConnected not property work, need more proper way to handle disconnect issue
			//these methods only tells you whether you closed or disconnected from the client not vice versa
			while(clientSocket.isClosed() == false) {
				String request = in.readLine();
				if(StringUtils.isBlank(request))
					continue;
				logger.debug("receiving action {}" , request);
				processRequest(request);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		} finally {
			//TODO hata alindiginda, socket kapaniyor, daha farkli bir yöntem yapilmali.
			try {
				releaseResource();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}			
		}
	}
	
	private void processRequest(String rawSocketMessage) {
		logger.debug("received message {}", rawSocketMessage);
		try {
			RequestMessage requestMessage = gson.fromJson(rawSocketMessage, RequestMessage.class);
			GameMessageProcessor.getInstance().processMessage(requestMessage, this);
//			ResponseMessage responseMessage = GameMessageProcessor.getInstance().processMessage(requestMessage, this);
//			sendMessage(responseMessage);				
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
//			throw new BatakException(GameExceptionMessage.TECHNICAL_PROBLEM);
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
