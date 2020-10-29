package tr.cobanse.client.gui;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.scene.layout.GridPane;
import tr.cobanse.batak.common.CardGame;
import tr.cobanse.batak.common.RequestMessage;
import tr.cobanse.batak.common.RequestType;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.batak.common.ResponseType;
import tr.cobanse.batak.common.processor.ResponseMessageProcessor;
import tr.cobanse.client.gui.listener.ClientSocket;
import tr.cobanse.client.gui.listener.GameSocketListener;

@SuppressWarnings("restriction")
public class GameRoomView  implements GameSocketListener {

	private ObservableList<GameRoom> gameRooms;
	private List<GameRoom> lstGameRooms = new ArrayList<>();
	private GridPane gridPane;
	private ClientSocket socket;
	private javafx.scene.control.Button button;
	
	public GameRoomView(ClientSocket socket) {
		this.socket = socket;
		this.gameRooms = FXCollections.observableArrayList(lstGameRooms);
		this.gridPane = new GridPane();
		button = new javafx.scene.control.Button("List games");
		button.setOnMouseClicked(new javafx.event.EventHandler<Event>() {
			public void handle(javafx.event.Event event) {
				listGame();
			};
		});
		this.gridPane.add(button, 0, 0); 
//		listGame();
//		lstGameRooms.stream().forEach((gameRoom)->{gridPane.getChildren().add(gameRoom.getvBox());});
	}
	
	private void listGame() {
		RequestMessage msg = new RequestMessage("", RequestType.LISTGAME);
		socket.sendRequestMessage(msg);
	}
	
	public void addGameView(GameRoom gameRoom) {
		this.gameRooms.add(gameRoom);
	}
	
	public GridPane getGridPane() {
		return gridPane;
	}
	
	@Override
	public void onEventReceived(ResponseMessage message) {
		Platform.runLater(()->{
			if(message.getResponseType().equals(ResponseType.LISTGAME)) {
				//only handles list game property
				List<String> cardGames = message.getAvailableGames();
				lstGameRooms.clear();
				for (String gameId : cardGames) {
					GameRoom gameRoom = new GameRoom(gameId);
					lstGameRooms.add(gameRoom);
				}
				//Platform.runLater(runnable);
				lstGameRooms.stream().forEach((gameRoom)->{gridPane.getChildren().add(gameRoom.getvBox());});
			}
		});
	}

}
