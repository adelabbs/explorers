package gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import data.entity.Explorer;

import data.simulation.Environment;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ExplorersInfosFX extends HBox {
	private Text health;
	
	public ExplorersInfosFX(double arg0) {
		super();
        try {
			displayInfos(arg0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void displayInfos(double arg0) throws FileNotFoundException {
		VBox expInfos = new VBox();
		VBox itemsInfos = new VBox();
		FileInputStream input = new FileInputStream("ressources/img/moving_explorer.gif");
        Image image = new Image(input);
		for(int i = 0; i < Environment.getInstance().getExplorerAmount(); i++) {
			if (Environment.getInstance().getEntities().get(i).getClass() == Explorer.class) {
				Explorer e = (Explorer) Environment.getInstance().getEntities().get(i);
				Text name = new Text(e.getName());
				name.setFont(Font.font("Arial", arg0 / 28));
		        name.setFill(Color.SIENNA);
		        name.setStroke(Color.SADDLEBROWN);
		        name.setTextOrigin(VPos.TOP);
		        
		        Text speed = new Text("Speed: " + String.valueOf(e.getSpeed()));
				speed.setFont(Font.font("Arial", arg0 / 36));
		        speed.setFill(Color.SIENNA);
		        speed.setStroke(Color.GOLDENROD);
		        speed.setTextOrigin(VPos.TOP);
		        
		        Text scope = new Text("Scope: " + String.valueOf(e.getScope()));
				scope.setFont(Font.font("Arial", arg0 / 36));
		        scope.setFill(Color.SIENNA);
		        scope.setStroke(Color.GOLDENROD);
		        scope.setTextOrigin(VPos.TOP);
		        
		        Text damage = new Text("Damage: " + String.valueOf(e.getDamage()));
				damage.setFont(Font.font("Arial", arg0 / 36));
		        damage.setFill(Color.SIENNA);
		        damage.setStroke(Color.GOLDENROD);
		        damage.setTextOrigin(VPos.TOP);
		        
		        Text comRange1 = new Text("Communication");
		        comRange1.setFont(Font.font("Arial", arg0 / 36));
		        comRange1.setFill(Color.SIENNA);
		        comRange1.setStroke(Color.GOLDENROD);
		        comRange1.setTextOrigin(VPos.TOP);
		        
		        Text comRange2 = new Text("range: " + String.valueOf(e.getCommunicationRange()));
				comRange2.setFont(Font.font("Arial", arg0 / 36));
		        comRange2.setFill(Color.SIENNA);
		        comRange2.setStroke(Color.GOLDENROD);
		        comRange2.setTextOrigin(VPos.TOP);
		        
		        health = new Text("Health: " + String.valueOf(e.getHealth()) + "/" + String.valueOf(e.getMaxHealth()));
				health.setFont(Font.font("Arial", arg0 / 36));
		        health.setFill(Color.SIENNA);
		        health.setStroke(Color.GOLDENROD);
		        health.setTextOrigin(VPos.TOP);
		        
		        ImageView movingExplorer = new ImageView(image);
		        movingExplorer.setFitHeight(arg0 / 12);
		        movingExplorer.setPreserveRatio(true);
		       
		        GridPane expGrid = new GridPane();
		        //expGrid.add(name, 0, 0); //column, lines
		        expGrid.add(speed, 0, 1);
		        expGrid.add(scope, 0, 2);
		        expGrid.add(damage, 0, 3);
		        expGrid.add(comRange1, 1, 1);
		        expGrid.add(comRange2, 1, 2);
		        expGrid.add(health, 1, 3);
		        
		        HBox expStats = new HBox();
		        expStats.getChildren().addAll(movingExplorer, expGrid);

				expInfos.getChildren().addAll(name, expStats);
			}						
		}
		this.getChildren().addAll(expInfos, itemsInfos);
	}
	
	public void updateInfos() {
		for(int i = 0; i < Environment.getInstance().getExplorerAmount(); i++) {
			if (Environment.getInstance().getEntities().get(i).getClass() == Explorer.class) {
				Explorer e = (Explorer) Environment.getInstance().getEntities().get(i);
				health.setText("Health: " + String.valueOf(e.getHealth()) + "/" + String.valueOf(e.getMaxHealth()));
 				System.out.println("Health " + i + " : " + String.valueOf(e.getHealth()) + "/" + String.valueOf(e.getMaxHealth()));
			}
		}
	}
}
