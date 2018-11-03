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

public class ClientSocket {

	private Logger logger = Logger.getLogger(ClientSocket.class);
	private Gson gson = new Gson();
	private BufferedReader in;
	private Socket socket;
	private PrintWriter out;
	private volatile boolean running = true;
	private List<GameSocketListener> listeners = new ArrayList<GameSocketListener>();
	
	public ClientSocket(String ip, int port) {
		try {
			connectToServer(ip, port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	@Override
//	public void run() {
//		ResponseMessage serverMessage = null;
//		try {
//			while(true) {
//				String msg = in.readLine();
//				logger.debug("received msg"+msg);
//				serverMessage = gson.fromJson(msg, ResponseMessage.class);
//				logger.debug(serverMessage.getMessage());
//				for (GameSocketListener gameSocketListener : listeners) {
//					gameSocketListener.onEventReceived(serverMessage); 
//				}
//			}
//		} catch (IOException e) {
//			logger.error("connection closed..." + e.getMessage());
//		}
//		if(in!=null) {
//			try {
//				in.close();
//			} catch (IOException e) {
//				logger.error("closing connection error..." + e.getMessage());
//			}
//		}
//	}

	private void connectToServer(String ip, int port) throws UnknownHostException, IOException {
		socket = new Socket(ip, port);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
	}
	
	public void sendRequestMessage(RequestMessage message) {
		String msg = gson.toJson(message);
		logger.debug("sending message to server...." + msg );
		out.println(msg);
	}
	
	public BufferedReader getIn() {
		return in;
	}
	/**
	 * register component that listens socket communication
	 * @param listener
	 */
	public void addSocketListener(GameSocketListener listener) {
		this.listeners.add(listener);
	}
	
	public void addMessageListener(RequestMessageSender messageListener) {
	}
}
