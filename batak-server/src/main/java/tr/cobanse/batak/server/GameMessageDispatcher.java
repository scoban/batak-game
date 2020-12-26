package tr.cobanse.batak.server;

import tr.cobanse.batak.common.ResponseMessage;

public interface GameMessageDispatcher {
	
	public void addSubscriber(String gameRoomId, Client client);
	
	public void sendMessageToAllPlayers(String gameRoomId, ResponseMessage message);
}
