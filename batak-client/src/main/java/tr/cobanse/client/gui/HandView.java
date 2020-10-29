package tr.cobanse.client.gui;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.layout.Pane;
import tr.cobanse.client.card.CardConstants;

/**
 * @author coban
 * view of user hands
 * opposite player hands will be showns as blank handview
 */
public class HandView {
	
	private List<CardView> availableCards = new ArrayList<>();
	private int width = 100;
	private int height = 100;
	private boolean preserveRatio = true;
	private DirectionType directionType;
	private Pane layout;
	
	public HandView(List<CardConstants> cards, DirectionType directionType, CardPool cardPool) throws FileNotFoundException {
		this.directionType = directionType;
		generateCardView(cards);
		generateHandView(availableCards);
	}
	
	public HandView(DirectionType directionType, CardPool cardPool) throws FileNotFoundException {
		this.directionType = directionType;
		List<CardConstants> cards = Arrays.asList(CardConstants.SINEK10,CardConstants.MACA2,CardConstants.KARO3);
		generateCardView(cards);
		generateHandView(availableCards);  
	}
	
	private void generateHandView(List<CardView> cards) {
		layout = DirectionTypeLayout.getDirectionPane(directionType);
		for (CardView cardView : cards) {
			cardView.addDragListener();
			layout.getChildren().add(cardView);
		}
	}

	private void generateCardView(List<CardConstants> cards) throws FileNotFoundException {
		for (CardConstants card : cards) {
			availableCards.add(new CardView(card.getUrl(), width, height, preserveRatio));
		}
	}
	
	public Pane getLayout() {
		return layout;
	}
}
