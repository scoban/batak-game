package tr.cobanse.client.gui;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import tr.cobanse.client.card.CardConstants;

public class CardPool extends StackPane{
	private List<CardView> poolCard;
	private StackPane pane;
	
	public CardPool() throws FileNotFoundException {
		poolCard = new ArrayList<CardView>(4);
		poolCard.addAll(Arrays.asList(new CardView(CardConstants.KARO10.getUrl(), 100, 100, true),
				new CardView(CardConstants.MACA10.getUrl(), 100, 100, true)));
		pane = new StackPane();
		poolCard.stream().forEach((cardView)->{
			pane.getChildren().add(cardView);
		});
	}

	@Override
	public Dragboard startDragAndDrop(TransferMode... transferModes) {
		return super.startDragAndDrop(TransferMode.MOVE);
	}
	
}
