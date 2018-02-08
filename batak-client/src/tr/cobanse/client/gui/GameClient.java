package tr.cobanse.client.gui;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GameClient extends Application{

	List<HandView> handViews;
	CardPool cardPool;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		cardPool = new CardPool();
		handViews = createHandsView();
		BorderPane root = new BorderPane(cardPool,handViews.get(0).getLayout(),
				handViews.get(1).getLayout(),handViews.get(2).getLayout(),handViews.get(3).getLayout());
		//create layout
		Scene scene = new Scene(root);
		scene.getStylesheets().add("file:./css/view.css");
		primaryStage.setTitle("Batak Game Client");
		primaryStage.setScene(scene);
		primaryStage.setWidth(800);
		primaryStage.setHeight(600);
		primaryStage.show();
	}
	
	private List<HandView> createHandsView() throws FileNotFoundException {
		return Arrays.asList(new HandView(DirectionType.WEST,cardPool),
				new HandView(DirectionType.NORTH,cardPool),
				new HandView(DirectionType.EAST,cardPool),
				new HandView(DirectionType.SOUTH,cardPool));
	}

	public static void main(String[] args) {
		launch(args);
	}
}
