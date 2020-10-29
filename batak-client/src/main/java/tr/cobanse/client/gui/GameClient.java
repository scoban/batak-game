package tr.cobanse.client.gui;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.application.*;
import tr.cobanse.client.gui.listener.ClientSocket;
import tr.cobanse.client.gui.listener.GameStageListener;

@SuppressWarnings("restriction")
public class GameClient extends Application implements GameStageListener{
	
	private Scene gameRoomViewScene;
	
	private Stage mainStage;
	
	private ClientSocket clientSocket;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		clientSocket = new ClientSocket("localhost", 60001);
		this.mainStage = primaryStage;
		GameRoomView roomView = new GameRoomView(clientSocket);
		gameRoomViewScene = new Scene(roomView.getGridPane());
		gameRoomViewScene.getStylesheets().add("file:./css/view.css");
		clientSocket.addSocketListener(roomView);
		primaryStage.setTitle("Batak Game Client");
		primaryStage.setScene(gameRoomViewScene);
		primaryStage.setWidth(800);
		primaryStage.setHeight(600);
		primaryStage.show();
	}
	
	public void onSceneChanged(Parent p) {
		Scene scene = new Scene(p);
		mainStage.setScene(scene);
		mainStage.show();
	}
	

	public static void main(String[] args) {
		launch(args);
	}

}
