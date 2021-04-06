package gui;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import data.simulation.SimulationEntry;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import process.Simulation;
import process.SimulationState;
import process.SimulationUtility;

/**
 * 
 * @author lespi
 * 
 * This class displays the HUD and all the informations available to the user. It is the graphical representation of the simulation.
 *
 */
public class SimulationFX {
	private Simulation simulation;
	private MapFX map;
	private EntitiesFX entities;
	private ExplorersInfosFX infos;
	private SimulationEntry simulationEntry;

	/**
	 * 
	 * @param primaryStage
	 * @param startButton
	 * @param enveloppe
	 * @param valueFactory
	 * @param strategy
	 * @param items
	 * @param primaryScreenBounds
	 * @param views
	 */
		public SimulationFX(Stage primaryStage, Button startButton, int enveloppe, SpinnerValueFactory<Integer> valueFactory, int strategy, HashMap<String, String> items,
				Rectangle2D primaryScreenBounds, ArrayList<ImageView> views, int stratNbr) {
			 AnimationTimer timer = new AnimationTimer() {
			/**
			* 
			* @param arg0
			*/
			
			@Override
		 	public void handle(long arg0) {
		 		SimulationUtility.unitTime();
		 		entities.clearShapes();
		 		simulation.update();
		 		try {
					infos.displayInfos(primaryScreenBounds.getHeight());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
		 		entities.drawShapes();
		 		map.drawExplorersGeneralMap();
		 		if (simulation.getState().equals(SimulationState.OVER)) {
		 			System.exit(0);
		 		}
		 		}  	  		 		
		    };
			startButton.setOnAction(actionEvent ->  {
	              if (enveloppe >= 0) {
	            		  this.simulationEntry = new SimulationEntry(valueFactory.getValue(), strategy, stratNbr);
	                  		if (items.containsKey("health"))
	                	  this.simulationEntry.add(items.get("health"));
	                  		if (items.containsKey("speed"))
	                	  this.simulationEntry.add(items.get("speed"));
	                  		if (items.containsKey("damage"))
	                	  this.simulationEntry.add(items.get("damage"));
	                  		if (items.containsKey("scope"))
	                	  this.simulationEntry.add(items.get("scope"));
	                  		if (items.containsKey("com"))
	                	  		this.simulationEntry.add(items.get("com"));
	                  		simulation = new Simulation(this.simulationEntry);
	                  		map = new MapFX(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
	                  		entities = new EntitiesFX(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
	                  
	                  		Group root = new Group();
	            	  		HUDFX hud;
	            	  		try {
	            		  		hud = new HUDFX(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
	            		  		HBox simuBox = new HBox(map);
	            		  		simuBox.setPadding(new Insets(primaryScreenBounds.getHeight() / 25, primaryScreenBounds.getHeight() / 30 , 0, primaryScreenBounds.getHeight() / 25));
	            		  
	            		  		HBox simuBox2 = new HBox(entities);
	            		  		simuBox2.setPadding(new Insets(primaryScreenBounds.getHeight() / 25, primaryScreenBounds.getHeight() / 30 , 0, primaryScreenBounds.getHeight() / 25));
	            		  
	            		  		infos = new ExplorersInfosFX(primaryScreenBounds.getHeight());
	            		  		infos.setPadding(new Insets(primaryScreenBounds.getHeight() / 12, primaryScreenBounds.getWidth() / 25 , 0, primaryScreenBounds.getWidth() / 1.75));
	            		  
	            		  		ObjectsInfosFX chosenItems = new ObjectsInfosFX(primaryScreenBounds.getHeight(), views.get(0), views.get(1), views.get(2), views.get(3), views.get(4));
	            		  		chosenItems.setPadding(new Insets(primaryScreenBounds.getHeight() / 12, primaryScreenBounds.getWidth() / 25 , 0, primaryScreenBounds.getWidth() / 1.14));
	            		  
	            		  		StackPane pane = new StackPane(simuBox,simuBox2, hud, infos, chosenItems);
	            		  		root.getChildren().add(pane); 
	            		  		primaryStage.setScene(new Scene(root, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight()));
	            	  
	            		  		simulation.launch();
	            		  		map.drawShapes();
	            		  		timer.start();
	            	  		} catch (FileNotFoundException e1) {
	            	  			e1.printStackTrace();
							}
	            	  	}
	              else {        	  
	            	  Tooltip tooltip = new Tooltip("The maximum amount of money is exceeded.");
	            	  startButton.setTooltip(tooltip);
	            	  tooltip.setShowDelay(Duration.millis(100));
	              }
	        });
		}
}
