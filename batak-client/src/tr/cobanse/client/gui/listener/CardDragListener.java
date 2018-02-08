package tr.cobanse.client.gui.listener;

import javafx.scene.Parent;
import tr.cobanse.client.gui.CardView;

public class CardDragListener {
	public void listen(CardView cardView) {
		double x = 0;
		double y = 0;
		System.out.println("x coordinate:"+x+",y coordinate:"+y);
		Parent parent = null;
		do{
			parent = cardView.getParent();
			x = parent.getLayoutX();
			y = parent.getLayoutX();
		}while(parent==null);
		cardView.setX(x);
		cardView.setY(y); 
	}
}
