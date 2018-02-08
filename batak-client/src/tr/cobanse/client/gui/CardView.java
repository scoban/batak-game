package tr.cobanse.client.gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class CardView extends ImageView {
	private String fileUrl;
	private Image image;
	private double width;
	private double height;
	private double orgSceneX, orgSceneY;
	private double orgTranslateX, orgTranslateY;

	public CardView(String fileUrl, double width, double height, boolean preserveRatio) throws FileNotFoundException {
		this.fileUrl = fileUrl;
		this.width = width;
		this.height = height;
		image = new Image(new FileInputStream(fileUrl), width, height, preserveRatio, false);
		setImage(image);
	}

	public String getFileUrl() {
		return fileUrl;
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
	
	public double getOrgSceneX() {
		return orgSceneX;
	}
	
	public double getOrgSceneY() {
		return orgSceneY;
	}
	
	public double getOrgTranslateX() {
		return orgTranslateX;
	}
	
	public double getOrgTranslateY() {
		return orgTranslateY;
	}

	public void setOrgSceneX(double orgSceneX) {
		this.orgSceneX = orgSceneX;
	}

	public void setOrgSceneY(double orgSceneY) {
		this.orgSceneY = orgSceneY;
	}

	public void setOrgTranslateX(double orgTranslateX) {
		this.orgTranslateX = orgTranslateX;
	}

	public void setOrgTranslateY(double orgTranslateY) {
		this.orgTranslateY = orgTranslateY;
	}
	
	
}
