package tr.cobanse.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.RequestType;
import tr.cobanse.client.gui.listener.ClientSocket;

public class TestServerAction {

	private Logger logger = LoggerFactory.getLogger(TestServerAction.class);
	
	ClientSocket clientSocket = new ClientSocket("localhost", 60001);
	
	@Before
	public void tearUp() throws UnknownHostException, IOException {
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
