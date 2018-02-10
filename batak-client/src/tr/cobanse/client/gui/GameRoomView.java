package tr.cobanse.client.gui;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.GridPane;
import tr.cobanse.batak.common.CardGame;
import tr.cobanse.batak.common.ResponseMessage;
import tr.cobanse.client.gui.listener.GameSocketListener;

public class GameRoomView implements GameSocketListener{

	private ObservableList<GameRoom> gameRooms;
	private List<GameRoom> lstGameRooms = new ArrayList<>();
	private static GameRoomView gameRoomView;
	private GridPane gridPane;
	
	private GameRoomView() {
		this.gameRooms = FXCollections.observableArrayList(lstGameRooms);
		this.gridPane = new GridPane();
		lstGameRooms.add(new GameRoom());
		lstGameRooms.stream().forEach((gameRoom)->{gridPane.getChildren().add(gameRoom.getvBox());});
	}
	
	public static GameRoomView getInstance() {
		if(gameRoomView==null)
			gameRoomView = new GameRoomView();
		return gameRoomView;
	}
	
	public void addGameView(GameRoom gameRoom) {
		this.gameRooms.add(gameRoom);
	}
	
	public GridPane getGridPane() {
		return gridPane;
	}
	
	@Override
	public void onEventReceived(ResponseMessage message) {
		//only handles list game property
		List<CardGame> cardGames = message.getAvailableGames();
		lstGameRooms.clear();
		for (CardGame cardGame : cardGames) {
			GameRoom gameRoom = new GameRoom();
			lstGameRooms.add(gameRoom);
		}
	}

}
