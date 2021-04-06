package gui;

import java.io.FileInputStream;

import java.io.FileNotFoundException;

import data.entity.Explorer;

import data.simulation.Environment;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * 
 * @author lespi
 * 
 * This class displays all the explorers's statistic on the HUD, along with the number of treasure chests.
 *
 */
public class ExplorersInfosFX extends VBox {
	private HBox expInfos;
	private VBox expStats1;
	private VBox expStats2;
	private Text comRange1;
	private Text comRange2;
	private HBox chestDisplay;
	
	private Text health;
	private Explorer e;
	
	/**
	 * 
	 * @param arg0
	 */
	public ExplorersInfosFX(double arg0) {
		super();
        try {
			displayInfos(arg0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param arg0
	 * @throws FileNotFoundException
	 */
	public void displayInfos(double arg0) throws FileNotFoundException {
		this.getChildren().clear();
		
		FileInputStream input0 = new FileInputStream("ressources/img/obstacles/treasure.png");
        Image image0 = new Image(input0);
        ImageView chestIcon = new ImageView(image0);
        chestIcon.setFitHeight(arg0 / 28);
        chestIcon.setPreserveRatio(true);
        
		Text chest = new Text("  " + Environment.getInstance().getFoundChest() + "/" + Environment.getInstance().getChestAmount());
		chest.setFont(Font.font("Arial", FontWeight.BOLD, arg0 / 28));
		chest.setFill(new Color(0.68, 0.48, 0.26, 1.0));

		chest.setTextOrigin(VPos.TOP);
		
		chestDisplay = new HBox();
		chestDisplay.getChildren().addAll(chestIcon, chest);
		chestDisplay.setPadding(new Insets(0, 0, 0, arg0/8));
        this.getChildren().add(chestDisplay);
        
		FileInputStream input = new FileInputStream("ressources/img/moving_explorer.gif");
        Image image = new Image(input);
		for(int i = 0; i < Environment.getInstance().getExplorerAmount(); i++) {
			if (Environment.getInstance().getEntities().get(i).getClass() == Explorer.class) {
				e = (Explorer) Environment.getInstance().getEntities().get(i);
				Text name = new Text(e.getName());
				name.setFont(Font.font("Arial", arg0 / 28));
		        name.setFill(Color.SADDLEBROWN);
		        name.setTextOrigin(VPos.TOP);
		        
		        Text speed = new Text("Speed: " + e.getSpeed());
				speed.setFont(Font.font("Arial", arg0 / 36));
		        speed.setFill(new Color(0.68, 0.48, 0.26, 1.0));
		        speed.setTextOrigin(VPos.TOP);
		        
		        Text scope = new Text("Scope: " + e.getScope());
				scope.setFont(Font.font("Arial", arg0 / 36));
		        scope.setFill(new Color(0.68, 0.48, 0.26, 1.0));
		        scope.setTextOrigin(VPos.TOP);
		        
		        Text damage = new Text("Damage: " + e.getDamage());
				damage.setFont(Font.font("Arial", arg0 / 36));
		        damage.setFill(new Color(0.68, 0.48, 0.26, 1.0));
		        damage.setTextOrigin(VPos.TOP);
		        
		        comRange1 = new Text("Communication");
		        comRange1.setFont(Font.font("Arial", arg0 / 36));
		        comRange1.setFill(new Color(0.68, 0.48, 0.26, 1.0));
		        comRange1.setTextOrigin(VPos.TOP);
		        
		        comRange2 = new Text("range: " + e.getCommunicationRange());
				comRange2.setFont(Font.font("Arial", arg0 / 36));
		        comRange2.setFill(new Color(0.68, 0.48, 0.26, 1.0));
		        comRange2.setTextOrigin(VPos.TOP);
		        		        
		        ImageView movingExplorer = new ImageView(image);
		        movingExplorer.setFitHeight(arg0 / 12);
		        movingExplorer.setPreserveRatio(true);
		        
		        health = new Text("Health: " + e.getHealth() + "/" + e.getMaxHealth());
				health.setFont(Font.font("Arial", arg0 / 36));
		        health.setFill(new Color(0.68, 0.48, 0.26, 1.0));
		        health.setTextOrigin(VPos.TOP);

		        expStats1 = new VBox();
		        expStats1.getChildren().addAll(speed, scope, damage);
		        
		        expStats2 = new VBox();
		        expStats2.getChildren().addAll(comRange1, comRange2, health);

		        expInfos = new HBox();
				expInfos.getChildren().addAll(movingExplorer, expStats1, expStats2);
				this.getChildren().addAll(name, expInfos);
			}						
		}		
	}
}
