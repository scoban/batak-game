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
	public CardView(String fileUrl, double width, double height, boolean preserveRatio) {
		this.fileUrl = fileUrl;
		try {
			image = new Image(new FileInputStream("./src/tr/cobanse/client/gui/karo-10.png"),width,height,preserveRatio,false);
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}
		setImage(image); 
	}
	
	public String getFileUrl() {
		return fileUrl;
	}
	
	@Override
	public Dragboard startDragAndDrop(TransferMode... transferModes) {
		return super.startDragAndDrop(transferModes);
	}
}
