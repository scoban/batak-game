package tr.cobanse.client.gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.RequestType;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.client.gui.listener.ClientSocket;
import tr.cobanse.client.gui.listener.GameSocketListener;

public class GameRoom implements GameSocketListener{
	
	private Logger logger = Logger.getLogger(GameRoom.class);
	private List<String> users;
	private VBox vBox;
	private Image img;
	private ImageView imgView = new ImageView();
	private String gameId;
	private String userId = UUID.randomUUID().toString();
	
	public GameRoom(String id) {
		this.gameId = id;
		vBox = new VBox();
		createGameRoomIcon();
		imgView.setOnMouseClicked((mouseEvent)->{
			RequestMessage message = new RequestMessage(userId, RequestType.JOIN, null, gameId);
			ClientSocket.getInstance().sendRequestMessage(message);
		});

		vBox.getChildren().add(imgView);
	}

	private void createGameRoomIcon() {
		try {
			img = new Image(new FileInputStream("./resources/backcard.jpg"),100,100,true,false);
		} catch (FileNotFoundException e) {
			logger.error("backcard resources not found");
		}
		imgView.setImage(img); 
	}
	
	public VBox getvBox() {
		return vBox;
	}
	
	@Override
	public void onEventReceived(ResponseMessage message) {
		System.out.println(message);
	}
}
