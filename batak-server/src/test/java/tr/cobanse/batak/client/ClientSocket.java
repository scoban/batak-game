package tr.cobanse.batak.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.client.gui.listener.ClientSocket;

public class ClientSocket {

	private Logger logger = LoggerFactory.getLogger(ClientSocket.class);
	private Gson gson = new Gson();
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	
	public ClientSocket(String ip, int port) {
		try {
			connectToServer(ip, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		new Thread(new SocketListener(in)).start();
	}
	
	class SocketListener implements Runnable{

		private Logger logger = LoggerFactory.getLogger(SocketListener.class);
		private BufferedReader listenerSocketReaderin;
		public SocketListener(BufferedReader in) {
			this.listenerSocketReaderin = in;
		}
		@Override
		public void run() {
			ResponseMessage serverMessage = null;
			try {
				while(true) {
					String msg = listenerSocketReaderin.readLine();
					if(msg==null) {
						continue;
					}
					logger.debug("received msg: {}", msg);
					serverMessage = gson.fromJson(msg, ResponseMessage.class);
					logger.debug(serverMessage.getMessage());
//					for (GameSocketListener gameSocketListener : listeners) {
//						gameSocketListener.onEventReceived(serverMessage); 
//					}
				}
			} catch (IOException e) {
				logger.error("connection closed...{}" , e.getMessage());
			}
			if(listenerSocketReaderin!=null) {
				try {
					listenerSocketReaderin.close();
				} catch (IOException e) {
					logger.error("closing connection error...{}" , e.getMessage());
				}
			}
		}
		
	}


	private void connectToServer(String ip, int port) throws UnknownHostException, IOException {
		socket = new Socket(ip, port);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
	}
	
	public void sendRequestMessage(RequestMessage message) {
		String msg = gson.toJson(message);
		logger.debug("sending message to server....{}" , msg );
		out.println(msg);
	}
	
	public BufferedReader getIn() {
		return in;
	}
	
	public static void main(String[] args) {
		 new ClientSocket("localhost", 60001);
	}
	
}
