package tr.cobanse.client.gui.listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.ResponseMessage;

public class ClientSocket implements Runnable{

	private Logger logger = Logger.getLogger(ClientSocket.class);
	private Gson gson = new Gson();
	private BufferedReader in;
	private Socket socket;
	private PrintWriter out;
	private volatile boolean running = true;
	private List<GameSocketListener> listeners = new ArrayList<GameSocketListener>();
	private static ClientSocket clientSocket;
	
	private ClientSocket() {
		
	}
	
	public static ClientSocket getInstance() {
		if(clientSocket==null) {
			clientSocket = new ClientSocket();
		}
		return clientSocket;
	}
	
	private void startListening() {
		if(socket==null)
			throw new IllegalStateException("socket is closed... can not listening...");
		new Thread(clientSocket).start();
	}
	
	@Override
	public void run() {
		ResponseMessage serverMessage = null;
		try {
			while(true) {
				String msg = in.readLine();
				serverMessage = gson.fromJson(msg, ResponseMessage.class);
				logger.debug(serverMessage.getMessage());
				for (GameSocketListener gameSocketListener : listeners) {
					gameSocketListener.onEventReceived(serverMessage); 
				}
			}
		} catch (IOException e) {
			logger.error("connection closed..." + e.getMessage());
		}
	}

	public void connectToServer(String ip, int port) throws UnknownHostException, IOException {
		socket = new Socket(ip, port);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		startListening();
	}
	
	public void sendRequestMessage(RequestMessage message) {
		String msg = gson.toJson(message);
		logger.debug("sending message to server...." + msg );
		out.println(msg);
	}
	
	/**
	 * register component that listens socket communication
	 * @param listener
	 */
	public void addSocketListener(GameSocketListener listener) {
		this.listeners.add(listener);
	}
}
