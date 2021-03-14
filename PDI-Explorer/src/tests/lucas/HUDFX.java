package tests.lucas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class HUDFX extends HBox {
	public HUDFX(double arg1) throws FileNotFoundException {
		super();
		FileInputStream input = new FileInputStream("ressources/img/HUD.png");
        Image image = new Image(input);
        ImageView hud = new ImageView(image);
        hud.setFitWidth(arg1);
        hud.setPreserveRatio(true);
		this.getChildren().add(hud);
	}
}
