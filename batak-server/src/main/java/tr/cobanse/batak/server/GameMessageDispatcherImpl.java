package tr.cobanse.batak.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.server.util.JsonUtil;

public class GameMessageDispatcherImpl implements GameMessageDispatcher{

	private static Logger logger = LoggerFactory.getLogger(GameMessageDispatcherImpl.class);
	
	private Map<String, List<Client>> connectionContainer = new HashMap<>();
	
	public void addSubscriber(String gameRoomId, Client client) {
		List<Client> clients = connectionContainer.getOrDefault(gameRoomId, new ArrayList<>());
		clients.add(client);
		connectionContainer.put(gameRoomId, clients);
	}
	
	public void sendMessageToAllPlayers(String gameRoomId, ResponseMessage message) {
		List<Client> clients = connectionContainer.get(gameRoomId);
		if(clients == null || clients.isEmpty())
			return;
		for (Client client : clients) {
			try {
				client.sendMessage(message);
			} catch (IOException e) {
				//TODO if user disconnects ??
				logger.info("error sending message to client in game room {}. message {}", gameRoomId, JsonUtil.toJson(message));
				logger.error(e.getMessage(), e);
			}
		}
	}
}
