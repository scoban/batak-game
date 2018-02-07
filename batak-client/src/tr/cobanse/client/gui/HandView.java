package tr.cobanse.client.gui;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.StackPane;
import tr.cobanse.batak.common.Card;
import tr.cobanse.client.card.CardConstants;

/**
 * @author coban
 * view of user hands
 * opposite player hands will be showns as blank handview
 */
public class HandView extends StackPane{
	
	private List<CardView> availableCards = new ArrayList<>();
	private int width = 100;
	private int height = 100;
	private boolean preserveRatio = true;
	
	public HandView(List<Card> cards) {
		generateCardView(cards);
		generateHandView(availableCards);
	}
	
	private void generateHandView(List<CardView> cards) {
		for (CardView cardView : cards) {
			getChildren().add(cardView);
		}
	}

	private void generateCardView(List<Card> cards) {
		for (Card card : cards) {
			availableCards.add(new CardView(CardConstants.KARO10.getUrl(), width, height, preserveRatio));
		}
	}
}
