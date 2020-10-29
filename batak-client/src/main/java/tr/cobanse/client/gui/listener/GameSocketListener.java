package tr.cobanse.client.gui.listener;

import tr.cobanse.batak.common.ResponseMessage;

public interface GameSocketListener {
	public void onEventReceived(ResponseMessage message);
}
