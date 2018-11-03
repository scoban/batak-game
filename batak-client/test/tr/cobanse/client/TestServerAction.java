package tr.cobanse.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.RequestType;
import tr.cobanse.client.gui.listener.ClientSocket;

public class TestServerAction {

	private Logger logger = Logger.getLogger(TestServerAction.class);
	
	ClientSocket clientSocket = new ClientSocket("localhost", 60001);
	
	@Before
	public void tearUp() throws UnknownHostException, IOException {
		BasicConfigurator.configure();
		SocketListener socketListener = new SocketListener(clientSocket);
		new Thread(socketListener).start();
	}
	@Test
	public void testAction() {
		logger.info("sending request to server");
		RequestMessage message = new RequestMessage("", RequestType.LISTGAME);
		clientSocket.sendRequestMessage(message);
		logger.info(String.format("Request sent:%s", new Gson().toJson(message)));
	}

	class SocketListener implements Runnable {
		private ClientSocket socket;

		public SocketListener(ClientSocket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try (BufferedReader is = new BufferedReader(socket.getIn())) {
				logger.info("waiting message from server");
				String readLine = is.readLine();
				System.out.println(readLine);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
