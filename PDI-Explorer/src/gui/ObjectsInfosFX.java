package gui;

import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * 
 * @author lespi
 *
 */
public class ObjectsInfosFX extends VBox {
	
	/**
	 * 
	 * @param arg0
	 * @param image1
	 * @param image2
	 * @param image3
	 * @param image4
	 * @param image5
	 */
	public ObjectsInfosFX(Double arg0, ImageView image1, ImageView image2, ImageView image3, ImageView image4, ImageView image5) {
		super(arg0 / 60);
		image1.setFitHeight(arg0 / 10);
		image1.setPreserveRatio(true);
        
		image2.setFitHeight(arg0 / 10);
		image2.setPreserveRatio(true);
        
		image3.setFitHeight(arg0 / 10);
		image3.setPreserveRatio(true);
        
		image4.setFitHeight(arg0 / 10);
		image4.setPreserveRatio(true);
        
		image5.setFitHeight(arg0 / 10);
		image5.setPreserveRatio(true);
        
		this.getChildren().addAll(image1, image2, image3, image4, image5);
	
	}
}
