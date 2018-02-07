package tr.cobanse.client.gui;

import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameClient extends Application{

	List<HandView> handViews;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		StackPane root = new StackPane();
		root.getChildren().add(new CardView("./src/tr/cobanse/client/gui/karo-10.png", 100, 100, true)); 
		Scene scene = new Scene(root);
		primaryStage.setTitle("Batak Game Client");
		primaryStage.setScene(scene);
		primaryStage.setWidth(800);
		primaryStage.setHeight(600);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
