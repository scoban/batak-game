package tr.cobanse.batak.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import tr.cobanse.batak.common.CardGame;
import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.server.action.ClientActionFactory;
import tr.cobanse.batak.server.action.PlayerAction;

public class Client implements Runnable {
	
	private Logger logger = Logger.getLogger(Client.class);
	
	private CardGame cardGame;
	private Socket clientSocket;
	private BufferedReader  in;
	private PrintWriter out;
	private Gson gson;
	private volatile boolean running = true;
	
	public Client(Socket socket, CardGame game) throws IOException {
		clientSocket = socket; 
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		out = new PrintWriter(clientSocket.getOutputStream(),true);
		cardGame = game;
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
			sendMessage(new ResponseMessage("Welcome to the batak game...", new ArrayList<>()));
			logger.debug("Welcome message sent");
			while(true) {
				String request = in.readLine();
				logger.debug("receiving action " + request);
				RequestMessage requestMessage = gson.fromJson(request, RequestMessage.class);
				PlayerAction action = ClientActionFactory.createAction(requestMessage.getRequestType());
				ResponseMessage message = action.execute(requestMessage);
				sendMessage(message);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

	private void sendMessage(ResponseMessage message) throws IOException {
		String msg = gson.toJson(message);
		logger.debug("sending message to client..." + msg);
		out.println(msg); 
	}
}
