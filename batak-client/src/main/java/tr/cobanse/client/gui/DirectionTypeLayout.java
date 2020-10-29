package tr.cobanse.client.gui;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class DirectionTypeLayout {
	
	public static Pane getDirectionPane(DirectionType directionType) {
		
		switch (directionType) { 
			case WEST:
				HBox west = new HBox(1d);
				west.setAlignment(Pos.CENTER);
				return west;
			case EAST:
				HBox east = new HBox(1d);
				east.setAlignment(Pos.CENTER);
				return east;
			case NORTH:
				VBox north = new VBox(1d);
				north.setAlignment(Pos.CENTER);
				return north;
				
			case SOUTH:
				VBox south = new VBox(1d);
				south.setAlignment(Pos.CENTER);
				return south;
				
				
			default:
				throw new IllegalArgumentException();
		}
	}
}
