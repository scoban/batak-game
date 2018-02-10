package tr.cobanse.client.gui;

import org.apache.log4j.BasicConfigurator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tr.cobanse.client.gui.listener.ClientSocket;

public class GameClient extends Application {
	
	/**
	 * list available games
	 */
	private Scene gameRoomViewScene;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//create layout
		ClientSocket.getInstance().connectToServer("localhost", 60001);
		gameRoomViewScene = new Scene(GameRoomView.getInstance().getGridPane());
		gameRoomViewScene.getStylesheets().add("file:./css/view.css");
		primaryStage.setTitle("Batak Game Client");
		primaryStage.setScene(gameRoomViewScene);
		primaryStage.setWidth(800);
		primaryStage.setHeight(600);
		primaryStage.show();
	}
	

	public static void main(String[] args) {
		BasicConfigurator.configure();
		launch(args);
	}

}
