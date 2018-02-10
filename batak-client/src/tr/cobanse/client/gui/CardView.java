package tr.cobanse.client.gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class CardView extends ImageView {
	private Image image;
	private double width;
	private double height;


	public CardView(String fileUrl, double width, double height, boolean preserveRatio) throws FileNotFoundException {
		this.width = width;
		this.height = height;
		this.image = new Image(new FileInputStream(fileUrl), width, height, preserveRatio, false);
		setImage(image);
	}

	@Override
	public Dragboard startDragAndDrop(TransferMode... transferModes) {
		return super.startDragAndDrop(TransferMode.MOVE);
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public void addDragListener() {
		new Draggable<CardView>(this);
	}
}
