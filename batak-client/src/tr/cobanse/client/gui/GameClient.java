package tr.cobanse.client.gui;

import org.apache.log4j.BasicConfigurator;

import javafx.scene.Scene;
import javafx.stage.Stage;
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
		GameRoomView roomView = new GameRoomView();
		gameRoomViewScene = new Scene(roomView.getGridPane());
		gameRoomViewScene.getStylesheets().add("file:./css/view.css");
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
		BasicConfigurator.configure();
		launch(args);
	}

}
