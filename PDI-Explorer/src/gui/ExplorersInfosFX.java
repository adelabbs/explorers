package gui;

import data.simulation.Environment;
import javafx.geometry.VPos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ExplorersInfosFX extends HBox {
	
	public ExplorersInfosFX(double arg0) {
		super();
		VBox expInfos = new VBox();
		VBox itemsInfos = new VBox();
		
		for(int i = 0; i < Environment.getInstance().getExplorerAmount(); i++) {
			Text name = new Text(Environment.getInstance().getEntities().get(i).getType());
			name.setFont(Font.font("Arial", arg0 / 24));
	        name.setFill(Color.SIENNA);
	        name.setStroke(Color.SADDLEBROWN);
	        name.setTextOrigin(VPos.TOP);
			expInfos.getChildren().addAll(name);
		}
		this.getChildren().addAll(expInfos, itemsInfos);
	}
}
