package tr.cobanse.client.gui;

import java.util.List;

import javafx.application.Platform;
import javafx.scene.layout.BorderPane;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.client.gui.listener.ClientSocket;
import tr.cobanse.client.gui.listener.GameSocketListener;

/**
 * @author coban
 * The view consists of 5 region. Edges of layout contains user information.
 * Center region will be a pool for discarded card.
 */
public class GameView extends BorderPane implements GameSocketListener{
	
	/**
	 * store only user who is actively display view
	 */
	private HandView handView;
	private List<String> userNames;
	
	public GameView(HandView handView) {
		//listens incoming response message
		ClientSocket.getInstance().addSocketListener(this);
	}

	@Override
	public void onEventReceived(ResponseMessage message) {
		Platform.runLater(()->{
			
		});
	}
	
	
}
