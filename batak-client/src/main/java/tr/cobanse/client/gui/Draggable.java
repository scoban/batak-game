package tr.cobanse.client.gui;

import javafx.scene.Node;

public class Draggable<T extends Node> {

	private Node node;
	private double orgSceneX, orgSceneY;
	private double orgTranslateX, orgTranslateY;
	
	public Draggable(T node) {
		this.node = node;
		setOnMousePressed();
		setOnMouseDragged();
	}
	
	private void setOnMousePressed() {
		node.setOnMousePressed((mouseEvent)->{
			orgSceneX = mouseEvent.getSceneX(); 
			orgSceneY = mouseEvent.getSceneY(); 
			orgTranslateX = node.getTranslateX();
			orgTranslateY = node.getTranslateY();
		});
	}
	
	private void setOnMouseDragged() {
		node.setOnMouseDragged((mouseEvent)->{
			double offsetX = mouseEvent.getSceneX() - orgSceneX;
	        double offsetY = mouseEvent.getSceneY() - orgSceneY;
	        double newTranslateX = orgTranslateX + offsetX;
	        double newTranslateY = orgTranslateY + offsetY;
	        node.setTranslateX(newTranslateX);
	        node.setTranslateY(newTranslateY);
		});
	}
	
	
	
}
