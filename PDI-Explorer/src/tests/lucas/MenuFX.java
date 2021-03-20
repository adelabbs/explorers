package tests.lucas;

import java.io.FileInputStream;


import java.io.FileNotFoundException;
import java.util.HashMap;
import data.simulation.SimulationEntry;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration; 
import process.Simulation;
import process.SimulationUtility;
import process.factory.ManagerFactory;

public class MenuFX extends Application {
	private int enveloppe = 180;
	
	private final Text text = new Text("Global envelope :");
	private Text text2 = new Text(String.valueOf(enveloppe));
	private final Text text3 = new Text("Number of explorers :");
	
	private Button startButton = new Button("START");
	private VBox enveloppeBox = new VBox();
    private Separator separator = new Separator(Orientation.VERTICAL);
    private TabPane tabPane = new TabPane();
    
    private ToggleButton healthButton1 = new ToggleButton("Nothing");
    private ToggleButton healthButton2 = new ToggleButton("Helmet");
    private ToggleButton healthButton3 = new ToggleButton("Chestplate");
    private ToggleButton speedButton1 = new ToggleButton("Nothing");
    private ToggleButton speedButton2 = new ToggleButton("Boots");
    private ToggleButton speedButton3 = new ToggleButton("Trecking Shoes");
    private ToggleButton damageButton1 = new ToggleButton("Nothing");
    private ToggleButton damageButton2 = new ToggleButton("Knife");
    private ToggleButton damageButton3 = new ToggleButton("Machete");
    private ToggleButton scopeButton1 = new ToggleButton("Nothing");
    private ToggleButton scopeButton2 = new ToggleButton("Glasses");
    private ToggleButton scopeButton3 = new ToggleButton("Binoculars");
    private ToggleButton comButton1 = new ToggleButton("Nothing");
    private ToggleButton comButton2 = new ToggleButton("Antenna");
    private ToggleButton comButton3 = new ToggleButton("Radio");

    private ToggleGroup comGroup = new ToggleGroup();
    private ToggleGroup scopeGroup = new ToggleGroup();
    private ToggleGroup damageGroup = new ToggleGroup();
    private ToggleGroup speedGroup = new ToggleGroup();
    private ToggleGroup healthGroup = new ToggleGroup();
    
    private final Text effectLabel1 = new Text("Effect : ");
    private final Text effectLabel2 = new Text("Effect : ");
    private final Text effectLabel3 = new Text("Effect : ");
    private final Text effectLabel4 = new Text("Effect : ");
    private final Text effectLabel5 = new Text("Effect : ");
    
    private Text healthEffect = new Text("None");
    private Text speedEffect = new Text("None");
    private Text damageEffect = new Text("None");
    private Text scopeEffect = new Text("None");
    private Text comEffect = new Text("None");
    
    private VBox healthBox = new VBox(healthButton1, healthButton2, healthButton3, effectLabel1, healthEffect);
    private VBox speedBox = new VBox(speedButton1, speedButton2, speedButton3, effectLabel2, speedEffect);
    private VBox damageBox = new VBox(damageButton1, damageButton2, damageButton3, effectLabel3, damageEffect);
    private VBox scopeBox = new VBox(scopeButton1, scopeButton2, scopeButton3, effectLabel4, scopeEffect);
    private VBox comBox = new VBox(comButton1, comButton2, comButton3, effectLabel5, comEffect);
    
    private Tab health = new Tab("Health", healthBox);
    private Tab speed = new Tab("Speed", speedBox);
    private Tab damage = new Tab("Damage", damageBox);
    private Tab scope = new Tab("Scope", scopeBox);
    private Tab com = new Tab("Communication Range", comBox);
	
	private int healthTemp = 0;
	private int speedTemp = 0;
	private int damageTemp = 0;
	private int scopeTemp = 0;
	private int comTemp = 0;
	
	private final Spinner<Integer> nbExplorers = new Spinner<Integer>();   
    private final int initialNb = 3;
    private SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 6, initialNb);
    
    private SimulationEntry simulationEntry;
	private static Simulation simulation;
    private static MapFX map;
    private static EntitiesFX entities;
    
    //private static final int EXPLORER_AMOUNT = 3;
	private static final int ANIMAL_AMOUNT = 3;
	private static final int CHEST_AMOUNT = 3;
	private static final int EXPLORATION_STRATEGY = ManagerFactory.ALL_ROUNDER_STRATEGY;
	
	private HashMap<String, String> items = new HashMap<String, String>();
	

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
    	
        primaryStage.setTitle("Autonomous and communicant explorers");
        
        primaryStage.initStyle(StageStyle.UNIFIED);
        primaryStage.setMaximized(true);  //plein écran avec bordures
      //  primaryStage.setFullScreen(true);
        primaryStage.setResizable(false);
        

        
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        
      
        text.setFont(Font.font("Arial", FontWeight.BOLD, primaryScreenBounds.getHeight() / 15));
        text.setFill(Color.GOLD);
        text.setStroke(Color.BROWN);
        text.setTextOrigin(VPos.TOP);
        
        text2.setFont(Font.font("Arial", FontWeight.BOLD, primaryScreenBounds.getHeight() / 10));
        text2.setFill(Color.GOLD);
        text2.setStroke(Color.DARKGOLDENROD);
        text2.setTextOrigin(VPos.CENTER);
        
        text3.setFont(Font.font("Arial", FontWeight.BOLD, primaryScreenBounds.getHeight() / 15));
        text3.setFill(Color.BROWN);
        text3.setStroke(Color.BURLYWOOD);
        text3.setTextOrigin(VPos.CENTER);
        
        effectLabel1.setFont(Font.font("Arial", FontWeight.BOLD, primaryScreenBounds.getHeight() / 15));
        effectLabel1.setFill(Color.BROWN);
        effectLabel1.setStroke(Color.BURLYWOOD);
        effectLabel1.setTextOrigin(VPos.CENTER);
        healthEffect.setFont(Font.font("Arial", FontWeight.BOLD, primaryScreenBounds.getHeight() / 25));
        healthEffect.setFill(Color.MOCCASIN);
        healthEffect.setTextOrigin(VPos.CENTER);
        
        effectLabel2.setFont(Font.font("Arial", FontWeight.BOLD, primaryScreenBounds.getHeight() / 15));
        effectLabel2.setFill(Color.BROWN);
        effectLabel2.setStroke(Color.BURLYWOOD);
        effectLabel2.setTextOrigin(VPos.CENTER);
        speedEffect.setFont(Font.font("Arial", FontWeight.BOLD, primaryScreenBounds.getHeight() / 25));
        speedEffect.setFill(Color.MOCCASIN);
        speedEffect.setTextOrigin(VPos.CENTER);
        
        effectLabel3.setFont(Font.font("Arial", FontWeight.BOLD, primaryScreenBounds.getHeight() / 15));
        effectLabel3.setFill(Color.BROWN);
        effectLabel3.setStroke(Color.BURLYWOOD);
        effectLabel3.setTextOrigin(VPos.CENTER);
        damageEffect.setFont(Font.font("Arial", FontWeight.BOLD, primaryScreenBounds.getHeight() / 25));
        damageEffect.setFill(Color.MOCCASIN);
        damageEffect.setTextOrigin(VPos.CENTER);
        
        effectLabel4.setFont(Font.font("Arial", FontWeight.BOLD, primaryScreenBounds.getHeight() / 15));
        effectLabel4.setFill(Color.BROWN);
        effectLabel4.setStroke(Color.BURLYWOOD);
        effectLabel4.setTextOrigin(VPos.CENTER);
        scopeEffect.setFont(Font.font("Arial", FontWeight.BOLD, primaryScreenBounds.getHeight() / 25));
        scopeEffect.setFill(Color.MOCCASIN);
        scopeEffect.setTextOrigin(VPos.CENTER);
        
        effectLabel5.setFont(Font.font("Arial", FontWeight.BOLD, primaryScreenBounds.getHeight() / 15));
        effectLabel5.setFill(Color.BROWN);
        effectLabel5.setStroke(Color.BURLYWOOD);
        effectLabel5.setTextOrigin(VPos.CENTER);
        comEffect.setFont(Font.font("Arial", FontWeight.BOLD, primaryScreenBounds.getHeight() / 25));
        comEffect.setFill(Color.MOCCASIN);
        comEffect.setTextOrigin(VPos.CENTER);
        
        
        FileInputStream input = new FileInputStream("ressources/img/Essai_explorateur.gif");
        Image image = new Image(input);
        ImageView explorerGIF = new ImageView(image);
        explorerGIF.setFitHeight(primaryScreenBounds.getHeight() / 5);
        explorerGIF.setPreserveRatio(true);
        
        FileInputStream input1 = new FileInputStream("ressources/img/aucun_objet.png");
        Image image1 = new Image(input1);
        ImageView healthView = new ImageView(image1);
        healthView .setFitHeight(primaryScreenBounds.getHeight() / 5);
        healthView .setPreserveRatio(true);
        healthBox.getChildren().add(healthView);
        
        FileInputStream input2 = new FileInputStream("ressources/img/aucun_objet.png");
        Image image2 = new Image(input2);
        ImageView speedView = new ImageView(image2);
        speedView.setFitHeight(primaryScreenBounds.getHeight() / 5);
        speedView.setPreserveRatio(true);
        speedBox.getChildren().add(speedView);
        
        FileInputStream input3 = new FileInputStream("ressources/img/aucun_objet.png");
        Image image3 = new Image(input3);
        ImageView damageView = new ImageView(image3);
        damageView.setFitHeight(primaryScreenBounds.getHeight() / 5);
        damageView.setPreserveRatio(true);
        damageBox.getChildren().add(damageView);
        
        FileInputStream input4 = new FileInputStream("ressources/img/aucun_objet.png");
        Image image4 = new Image(input4);
        ImageView scopeView = new ImageView(image4);
        scopeView.setFitHeight(primaryScreenBounds.getHeight() / 5);
        scopeView.setPreserveRatio(true);
        scopeBox.getChildren().add(scopeView);
        
        FileInputStream input5 = new FileInputStream("ressources/img/aucun_objet.png");
        Image image5 = new Image(input5);
        ImageView comView = new ImageView(image5);
        comView.setFitHeight(primaryScreenBounds.getHeight() / 5);
        comView.setPreserveRatio(true);
        comBox.getChildren().add(comView);
  
        nbExplorers.setValueFactory(valueFactory);
        
        enveloppeBox.getChildren().add(text);
        enveloppeBox.getChildren().add(text2);
        enveloppeBox.getChildren().add(text3);
        enveloppeBox.getChildren().add(explorerGIF);
        enveloppeBox.getChildren().add(nbExplorers);
        enveloppeBox.getChildren().add(startButton);
        enveloppeBox.setAlignment(Pos.BASELINE_CENTER);
        enveloppeBox.setSpacing(40);
        

        healthButton1.setToggleGroup(healthGroup);
        healthButton2.setToggleGroup(healthGroup);
        healthButton3.setToggleGroup(healthGroup);
              
        healthBox.setAlignment(Pos.BASELINE_CENTER);
        

        speedButton1.setToggleGroup(speedGroup);
        speedButton2.setToggleGroup(speedGroup);
        speedButton3.setToggleGroup(speedGroup);
        
        speedBox.setAlignment(Pos.BASELINE_CENTER);


        damageButton1.setToggleGroup(damageGroup);
        damageButton2.setToggleGroup(damageGroup);
        damageButton3.setToggleGroup(damageGroup);
        
        damageBox.setAlignment(Pos.BASELINE_CENTER);
        

        scopeButton1.setToggleGroup(scopeGroup);
        scopeButton2.setToggleGroup(scopeGroup);
        scopeButton3.setToggleGroup(scopeGroup);
        
        scopeBox.setAlignment(Pos.BASELINE_CENTER);


        comButton1.setToggleGroup(comGroup);
        comButton2.setToggleGroup(comGroup);
        comButton3.setToggleGroup(comGroup);
        
        comBox.setAlignment(Pos.BASELINE_CENTER);
        
        healthButton1.setSelected(true);
        speedButton1.setSelected(true);
        damageButton1.setSelected(true);
        scopeButton1.setSelected(true);
        comButton1.setSelected(true);
        
        healthGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov, Toggle toggle,Toggle new_toggle) {
            	if (healthButton1.isSelected() == false) {
            		if (healthButton2.isSelected() == true) {
            			if (healthTemp == 0) {
            				items.put("health", "Helmet");
            				enveloppe -= 5*nbExplorers.getValue();
            			}
            			else if (healthTemp == 2) {
            				enveloppe += 5*nbExplorers.getValue();
            			}
            			healthTemp = 1;
            			healthEffect.setText("Health +");
            		}
            		else if (healthButton3.isSelected() == true) {
            			items.put("health", "Chestplate");
            			if (healthTemp == 0) {
            				enveloppe -= 10*nbExplorers.getValue();
            			}
            			else if (healthTemp == 1) {
            				enveloppe -= 5*nbExplorers.getValue();
            			}
            			healthTemp = 2;
            			healthEffect.setText("Health ++");
            		}
        		}
            	else if (healthButton1.isSelected() == true) {
            		items.remove("health");
            		if (healthTemp == 1) {
        				enveloppe += 5*nbExplorers.getValue();
        			}
        			else if (healthTemp == 2) {
        				enveloppe += 10*nbExplorers.getValue();
        			}
            		healthTemp = 0;
            		healthEffect.setText("None");
            	}
            	text2.setText(String.valueOf(enveloppe));
            }
        });
        speedGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov, Toggle toggle,Toggle new_toggle) {
            	if (speedButton1.isSelected() == false) {
            		if (speedButton2.isSelected() == true) {
            			items.put("speed", "Boots");
            			if (speedTemp == 0) {
            				enveloppe -= 5*nbExplorers.getValue();
            			}
            			else if (speedTemp == 2) {
            				enveloppe += 5*nbExplorers.getValue();
            			}
            			speedTemp = 1;
            			speedEffect.setText("Speed +");
            		}
            		else if (speedButton3.isSelected() == true) {
            			items.put("speed", "Trecking_shoes");
            			if (speedTemp == 0) {
            				enveloppe -= 10*nbExplorers.getValue();
            			}
            			else if (speedTemp == 1) {
            				enveloppe -= 5*nbExplorers.getValue();
            			}
            			speedTemp = 2;
            			speedEffect.setText("Speed ++");
            		}
        		}
            	else if (speedButton1.isSelected() == true) {
            		items.remove("speed");
            		if (speedTemp == 1) {
        				enveloppe += 5*nbExplorers.getValue();
        			}
        			else if (speedTemp == 2) {
        				enveloppe += 10*nbExplorers.getValue();
        			}
            		speedTemp = 0;
            		speedEffect.setText("None");
            	}
            	text2.setText(String.valueOf(enveloppe));
            }
        });
        damageGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov, Toggle toggle,Toggle new_toggle) {
            	if (damageButton1.isSelected() == false) {
            		if (damageButton2.isSelected() == true) {
            			items.put("damage", "Knife");
            			if (damageTemp == 0) {
            				enveloppe -= 5*nbExplorers.getValue();
            			}
            			else if (damageTemp == 2) {
            				enveloppe += 5*nbExplorers.getValue();
            			}
            			damageTemp = 1;
            			damageEffect.setText("Damage +");
            		}
            		else if (damageButton3.isSelected() == true) {
            			items.put("damage", "Machete");
            			if (damageTemp == 0) {
            				enveloppe -= 10*nbExplorers.getValue();
            			}
            			else if (damageTemp == 1) {
            				enveloppe -= 5*nbExplorers.getValue();
            			}
            			damageTemp = 2;
            			damageEffect.setText("Damage ++");
            		}
        		}
            	else if (damageButton1.isSelected() == true) {
            		items.remove("damage");
            		if (damageTemp == 1) {
        				enveloppe += 5*nbExplorers.getValue();
        			}
        			else if (damageTemp == 2) {
        				enveloppe += 10*nbExplorers.getValue();
        			}
            		damageTemp = 0;
            		damageEffect.setText("None");
            	}
            	text2.setText(String.valueOf(enveloppe));
            }
        });
        scopeGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov, Toggle toggle,Toggle new_toggle) {
            	if (scopeButton1.isSelected() == false) {
            		if (scopeButton2.isSelected() == true) {
            			items.put("scope", "Glasses");
            			if (scopeTemp == 0) {
            				enveloppe -= 5*nbExplorers.getValue();
            			}
            			else if (scopeTemp == 2) {
            				enveloppe += 5*nbExplorers.getValue();
            			}
            			scopeTemp = 1;
            			scopeEffect.setText("Scope +");
            		}
            		else if (scopeButton3.isSelected() == true) {
        				items.put("scope", "Binoculars");
            			if (scopeTemp == 0) {
            				enveloppe -= 10*nbExplorers.getValue();
            			}
            			else if (scopeTemp == 1) {
            				enveloppe -= 5*nbExplorers.getValue();
            			}
            			scopeTemp = 2;
            			scopeEffect.setText("Scope ++");
            		}
        		}
            	else if (scopeButton1.isSelected() == true) {
            		items.remove("scope");
            		if (scopeTemp == 1) {
        				enveloppe += 5*nbExplorers.getValue();
        			}
        			else if (scopeTemp == 2) {
        				enveloppe += 10*nbExplorers.getValue();
        			}
            		scopeTemp = 0;
            		scopeEffect.setText("None");
            	}
            	text2.setText(String.valueOf(enveloppe));
            }
        });
        comGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov, Toggle toggle,Toggle new_toggle) {
            	if (comButton1.isSelected() == false) {
            		if (comButton2.isSelected() == true) {
            			items.put("com", "Antenna");
            			if (comTemp == 0) {
            				enveloppe -= 5*nbExplorers.getValue();
            			}
            			else if (comTemp == 2) {
            				enveloppe += 5*nbExplorers.getValue();
            			}
            			comTemp = 1;
            			comEffect.setText("Communication Range +");
            		}
            		else if (comButton3.isSelected() == true) {
            			items.put("com", "Radio");
            			if (comTemp == 0) {
            				enveloppe -= 10*nbExplorers.getValue();
            			}
            			else if (comTemp == 1) {
            				enveloppe -= 5*nbExplorers.getValue();
            			}
            			comTemp = 2;
            			comEffect.setText("Communication Range ++");
            		}
        		}
            	else if (comButton1.isSelected() == true) {
            		items.remove("com");
            		if (comTemp == 1) {
        				enveloppe += 5*nbExplorers.getValue();
        			}
        			else if (comTemp == 2) {
        				enveloppe += 10*nbExplorers.getValue();
        			}
            		comTemp = 0;
            		comEffect.setText("None");
            	}
            	text2.setText(String.valueOf(enveloppe));
            }
        });
        
        nbExplorers.valueProperty().addListener(new ChangeListener<Integer>() {    	 
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
            	if (oldValue < newValue) {
            		if (healthTemp == 1) {
            			enveloppe -= 5;
            		}
            		else if (healthTemp == 2) {
            			enveloppe -= 10;
            		}
            		if (speedTemp == 1) {
            			enveloppe -= 5;
            		}
            		else if (speedTemp == 2) {
            			enveloppe -= 10;
            		}
            		if (damageTemp == 1) {
            			enveloppe -= 5;
            		}
            		else if (damageTemp == 2) {
            			enveloppe -= 10;
            		}
            		if (scopeTemp == 1) {
            			enveloppe -= 5;
            		}
            		else if (scopeTemp == 2) {
            			enveloppe -= 10;
            		}
            		if (comTemp == 1) {
            			enveloppe -= 5;
            		}
            		else if (comTemp == 2) {
            			enveloppe -= 10;
            		}
            	}
            	else if (oldValue > newValue) {
            		if (healthTemp == 1) {
            			enveloppe += 5;
            		}
            		else if (healthTemp == 2) {
            			enveloppe += 10;
            		}
            		if (speedTemp == 1) {
            			enveloppe += 5;
            		}
            		else if (speedTemp == 2) {
            			enveloppe += 10;
            		}
            		if (damageTemp == 1) {
            			enveloppe += 5;
            		}
            		else if (damageTemp == 2) {
            			enveloppe += 10;
            		}
            		if (scopeTemp == 1) {
            			enveloppe += 5;
            		}
            		else if (scopeTemp == 2) {
            			enveloppe += 10;
            		}
            		if (comTemp == 1) {
            			enveloppe += 5;
            		}
            		else if (comTemp == 2) {
            			enveloppe += 10;
            		}
            	}
            	healthButton1.setSelected(true);
        		speedButton1.setSelected(true);
        		damageButton1.setSelected(true);
        		scopeButton1.setSelected(true);
        		comButton1.setSelected(true);
            }
        });
        
        AnimationTimer timer = new AnimationTimer() {

 			@Override
 			public void handle(long arg0) {
 				SimulationUtility.unitTime();
 				entities.clearShapes();
 				simulation.update();
 				//map.drawShapes();
 				entities.drawShapes();
 				
 			}
     	   
        };
        

        startButton.setOnAction(actionEvent ->  {
              if (enveloppe >= 0) {
                  simulationEntry = new SimulationEntry(valueFactory.getValue(), ANIMAL_AMOUNT, CHEST_AMOUNT, EXPLORATION_STRATEGY);
                  if (items.containsKey("health"))
                	  simulationEntry.add(items.get("health"));
                  if (items.containsKey("speed"))
                	  simulationEntry.add(items.get("speed"));
                  if (items.containsKey("damage"))
                	  simulationEntry.add(items.get("damage"));
                  if (items.containsKey("scope"))
                	  simulationEntry.add(items.get("scope"));
                  if (items.containsKey("com"))
                	  simulationEntry.add(items.get("com"));
                  simulation = new Simulation(simulationEntry);
                  map = new MapFX(primaryScreenBounds.getWidth() /*- (primaryScreenBounds.getWidth() - primaryScreenBounds.getHeight())*/, primaryScreenBounds.getHeight());
                  entities = new EntitiesFX(primaryScreenBounds.getWidth() /*- (primaryScreenBounds.getWidth() - primaryScreenBounds.getHeight())*/, primaryScreenBounds.getHeight());
                  
            	  Group root = new Group();
            	  HUDFX hud;
            	  try {
            		  hud = new HUDFX(primaryScreenBounds.getWidth());
            		  HBox simuBox = new HBox(map);
            		  simuBox.setPadding(new Insets(primaryScreenBounds.getHeight() / 25, primaryScreenBounds.getHeight() / 30 , 0, primaryScreenBounds.getHeight() / 25));
            		  
            		  HBox simuBox2 = new HBox(entities);
            		  simuBox2.setPadding(new Insets(primaryScreenBounds.getHeight() / 25, primaryScreenBounds.getHeight() / 30 , 0, primaryScreenBounds.getHeight() / 25));
            		  
            		  StackPane pane = new StackPane(simuBox,simuBox2, hud);
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
            	 /* Alert errorAlert = new Alert(AlertType.ERROR);
            	  errorAlert.setContentText("The maximum amount of money is exceeded.");
            	  errorAlert.show(); */
            	  
            	  Tooltip tooltip = new Tooltip("The maximum amount of money is exceeded.");
            	  startButton.setTooltip(tooltip);
            	  tooltip.setShowDelay(Duration.millis(100));
              }
        });
        
        healthBox.setPadding(new Insets(primaryScreenBounds.getHeight() / 15, 0, 0, 0));
        healthBox.setSpacing(primaryScreenBounds.getHeight() / 15);
        speedBox.setPadding(new Insets(primaryScreenBounds.getHeight() / 15, 0, 0, 0));
        speedBox.setSpacing(primaryScreenBounds.getHeight() / 15);
        damageBox.setPadding(new Insets(primaryScreenBounds.getHeight() / 15, 0, 0, 0));
        damageBox.setSpacing(primaryScreenBounds.getHeight() / 15);
        scopeBox.setPadding(new Insets(primaryScreenBounds.getHeight() / 15, 0, 0, 0));
        scopeBox.setSpacing(primaryScreenBounds.getHeight() / 15);
        comBox.setPadding(new Insets(primaryScreenBounds.getHeight() / 15, 0, 0, 0));
        comBox.setSpacing(primaryScreenBounds.getHeight() / 15);

        tabPane.getTabs().add(health);
        tabPane.getTabs().add(speed);
        tabPane.getTabs().add(damage);
        tabPane.getTabs().add(scope);
        tabPane.getTabs().add(com);
        
        HBox hBox = new HBox(enveloppeBox, separator, tabPane);
        hBox.setPadding(new Insets(primaryScreenBounds.getHeight() / 15, primaryScreenBounds.getWidth() / 15, primaryScreenBounds.getHeight() / 15, primaryScreenBounds.getWidth() / 15));
        hBox.setSpacing(primaryScreenBounds.getWidth() / 10);      
        
        StackPane stack = new StackPane();
        stack.getChildren().addAll(new Rectangle(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight(), Color.OLIVEDRAB), hBox);
        
        Scene scene = new Scene(stack, primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());

        scene.setCursor(Cursor.OPEN_HAND); //Pourra être remplacé par un curseur personnalisé

        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        
        primaryStage.show();
        primaryStage.toFront();
 
       	primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
        	@Override
        	public void handle(WindowEvent e) {
        		System.exit(0);
        	}
        });
    }         
   
     public static void main(String[] args) {
    	 launch(args);
    } 
}