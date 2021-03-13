package tests.lucas;

import java.io.FileInputStream;


import java.io.FileNotFoundException;

import data.simulation.SimulationEntry;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
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
import javafx.util.Duration; 
import process.Simulation;

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
	private Simulation simulation;
    private DashboardFX dashboard;
    
    //private static final int EXPLORER_AMOUNT = 3;
	private static final int ANIMAL_AMOUNT = 3;
	private static final int CHEST_AMOUNT = 3;
	private static final int EXPLORATION_STRATEGY = 5;
	

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
  	  
    	simulationEntry = new SimulationEntry(3, ANIMAL_AMOUNT, CHEST_AMOUNT, EXPLORATION_STRATEGY);
    	simulation = new Simulation(simulationEntry);
    	
        primaryStage.setTitle("Autonomous and communicant explorers");
        
        primaryStage.initStyle(StageStyle.UNIFIED);
        //primaryStage.setMaximized(true);  //plein écran avec bordures
        primaryStage.setResizable(false);
        
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        dashboard = new DashboardFX(primaryScreenBounds.getWidth(), primaryScreenBounds.getHeight());
      
        text.setFont(Font.font("Arial", FontWeight.BOLD, primaryScreenBounds.getHeight() / 30));
        text.setFill(Color.GOLD);
        text.setStroke(Color.BROWN);
        text.setTextOrigin(VPos.TOP);
        
        text2.setFont(Font.font("Arial", FontWeight.BOLD, primaryScreenBounds.getHeight() / 20));
        text2.setFill(Color.GOLD);
        text2.setStroke(Color.DARKGOLDENROD);
        text2.setTextOrigin(VPos.CENTER);
        
        text3.setFont(Font.font("Arial", FontWeight.BOLD, primaryScreenBounds.getHeight() / 30));
        text3.setFill(Color.BROWN);
        text3.setStroke(Color.BURLYWOOD);
        text3.setTextOrigin(VPos.CENTER);
        
        effectLabel1.setFont(Font.font("Arial", FontWeight.BOLD, primaryScreenBounds.getHeight() / 30));
        effectLabel1.setFill(Color.BROWN);
        effectLabel1.setStroke(Color.BURLYWOOD);
        effectLabel1.setTextOrigin(VPos.CENTER);
        healthEffect.setFont(Font.font("Arial", FontWeight.BOLD, primaryScreenBounds.getHeight() / 50));
        healthEffect.setFill(Color.MOCCASIN);
        healthEffect.setTextOrigin(VPos.CENTER);
        
        effectLabel2.setFont(Font.font("Arial", FontWeight.BOLD, primaryScreenBounds.getHeight() / 30));
        effectLabel2.setFill(Color.BROWN);
        effectLabel2.setStroke(Color.BURLYWOOD);
        effectLabel2.setTextOrigin(VPos.CENTER);
        speedEffect.setFont(Font.font("Arial", FontWeight.BOLD, primaryScreenBounds.getHeight() / 50));
        speedEffect.setFill(Color.MOCCASIN);
        speedEffect.setTextOrigin(VPos.CENTER);
        
        effectLabel3.setFont(Font.font("Arial", FontWeight.BOLD, primaryScreenBounds.getHeight() / 30));
        effectLabel3.setFill(Color.BROWN);
        effectLabel3.setStroke(Color.BURLYWOOD);
        effectLabel3.setTextOrigin(VPos.CENTER);
        damageEffect.setFont(Font.font("Arial", FontWeight.BOLD, primaryScreenBounds.getHeight() / 50));
        damageEffect.setFill(Color.MOCCASIN);
        damageEffect.setTextOrigin(VPos.CENTER);
        
        effectLabel4.setFont(Font.font("Arial", FontWeight.BOLD, primaryScreenBounds.getHeight() / 30));
        effectLabel4.setFill(Color.BROWN);
        effectLabel4.setStroke(Color.BURLYWOOD);
        effectLabel4.setTextOrigin(VPos.CENTER);
        scopeEffect.setFont(Font.font("Arial", FontWeight.BOLD, primaryScreenBounds.getHeight() / 50));
        scopeEffect.setFill(Color.MOCCASIN);
        scopeEffect.setTextOrigin(VPos.CENTER);
        
        effectLabel5.setFont(Font.font("Arial", FontWeight.BOLD, primaryScreenBounds.getHeight() / 30));
        effectLabel5.setFill(Color.BROWN);
        effectLabel5.setStroke(Color.BURLYWOOD);
        effectLabel5.setTextOrigin(VPos.CENTER);
        comEffect.setFont(Font.font("Arial", FontWeight.BOLD, primaryScreenBounds.getHeight() / 50));
        comEffect.setFill(Color.MOCCASIN);
        comEffect.setTextOrigin(VPos.CENTER);
        
        
        FileInputStream input = new FileInputStream("ressources/img/Essai_explorateur.gif");
        Image image = new Image(input);
        ImageView explorerGIF = new ImageView(image);
        explorerGIF.setFitHeight(primaryScreenBounds.getHeight() / 10);
        explorerGIF.setPreserveRatio(true);
        
        FileInputStream input1 = new FileInputStream("ressources/img/aucun_objet.png");
        Image image1 = new Image(input1);
        ImageView healthView = new ImageView(image1);
        healthView .setFitHeight(primaryScreenBounds.getHeight() / 10);
        healthView .setPreserveRatio(true);
        healthBox.getChildren().add(healthView);
        
        FileInputStream input2 = new FileInputStream("ressources/img/aucun_objet.png");
        Image image2 = new Image(input2);
        ImageView speedView = new ImageView(image2);
        speedView.setFitHeight(primaryScreenBounds.getHeight() / 10);
        speedView.setPreserveRatio(true);
        speedBox.getChildren().add(speedView);
        
        FileInputStream input3 = new FileInputStream("ressources/img/aucun_objet.png");
        Image image3 = new Image(input3);
        ImageView damageView = new ImageView(image3);
        damageView.setFitHeight(primaryScreenBounds.getHeight() / 10);
        damageView.setPreserveRatio(true);
        damageBox.getChildren().add(damageView);
        
        FileInputStream input4 = new FileInputStream("ressources/img/aucun_objet.png");
        Image image4 = new Image(input4);
        ImageView scopeView = new ImageView(image4);
        scopeView.setFitHeight(primaryScreenBounds.getHeight() / 10);
        scopeView.setPreserveRatio(true);
        scopeBox.getChildren().add(scopeView);
        
        FileInputStream input5 = new FileInputStream("ressources/img/aucun_objet.png");
        Image image5 = new Image(input5);
        ImageView comView = new ImageView(image5);
        comView.setFitHeight(primaryScreenBounds.getHeight() / 10);
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
            				enveloppe -= 5*nbExplorers.getValue();
            			}
            			else if (healthTemp == 2) {
            				enveloppe += 5*nbExplorers.getValue();
            			}
            			healthTemp = 1;
            			healthEffect.setText("Health +");
            		}
            		else if (healthButton3.isSelected() == true) {
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
        
        startButton.setOnAction(actionEvent ->  {
              if (enveloppe >= 0) {
            	  Group root = new Group();
            	  root.getChildren().add(dashboard); 
            	  primaryStage.setScene(new Scene(root));
            	  
            	/* Task<Void> task = new Task<Void>() {
                      @Override
                      public Void call() throws Exception {
                    	  System.out.println("0");
                    	  simulation.launch();
                    	  System.out.println("1");
                          return null ;
                      }
                  };

                  task.setOnSucceeded(event -> {
                	  while (simulation.isRunning()) {
  		 				System.out.println("2");
  		 				SimulationUtility.unitTime();
  		 				System.out.println("3");
  		 				simulation.update();
  		 				System.out.println("4");
  		 				dashboard.drawShapes();
  		 				System.out.println("5");
                	  }
                  });

                  new Thread(task).run(); */
            	  
            	  
            	  /*Service<Void> simLaunch = new Service<Void>(){

            		  @Override
            		  protected Task<Void> createTask() {
            		    return new Task<Void>(){

            		     @Override
            		     protected Void call() throws Exception {
            		    	System.out.println("0");
            		    	simulation.launch();
            		    	System.out.println("1");
            		 			while (simulation.isRunning()) {
            		 				System.out.println("2");
            		 				SimulationUtility.unitTime();
            		 				System.out.println("3");
            		 				simulation.update();
            		 				System.out.println("4");
            		 				dashboard.drawShapes();
            		 				System.out.println("5");
            		 		}
                		    System.out.println("6");
            		        return null;
            		      }
            		    };
            		  }
            		};
            		simLaunch.start(); 
            		System.out.println("7"); */
              }
              else {
            	 /* Alert errorAlert = new Alert(AlertType.ERROR);
            	  errorAlert.setHeaderText("Input not valid");
            	  errorAlert.setContentText("The maximum amount of money is exceeded.");
            	  errorAlert.showAndWait(); */
            	  
            	  Tooltip tooltip = new Tooltip("The maximum amount of money is exceeded.");
            	  startButton.setTooltip(tooltip);
            	  tooltip.setShowDelay(Duration.millis(100));
              }
        });
        
        healthBox.setPadding(new Insets(primaryScreenBounds.getHeight() / 30, 0, 0, 0));
        healthBox.setSpacing(primaryScreenBounds.getHeight() / 30);
        speedBox.setPadding(new Insets(primaryScreenBounds.getHeight() / 30, 0, 0, 0));
        speedBox.setSpacing(primaryScreenBounds.getHeight() / 30);
        damageBox.setPadding(new Insets(primaryScreenBounds.getHeight() / 30, 0, 0, 0));
        damageBox.setSpacing(primaryScreenBounds.getHeight() / 30);
        scopeBox.setPadding(new Insets(primaryScreenBounds.getHeight() / 30, 0, 0, 0));
        scopeBox.setSpacing(primaryScreenBounds.getHeight() / 30);
        comBox.setPadding(new Insets(primaryScreenBounds.getHeight() / 30, 0, 0, 0));
        comBox.setSpacing(primaryScreenBounds.getHeight() / 30);

        tabPane.getTabs().add(health);
        tabPane.getTabs().add(speed);
        tabPane.getTabs().add(damage);
        tabPane.getTabs().add(scope);
        tabPane.getTabs().add(com);
        
        HBox hBox = new HBox(enveloppeBox, separator, tabPane);
        hBox.setPadding(new Insets(primaryScreenBounds.getHeight() / 30, primaryScreenBounds.getWidth() / 30, primaryScreenBounds.getHeight() / 30, primaryScreenBounds.getWidth() / 30));
        hBox.setSpacing(primaryScreenBounds.getWidth() / 30);      
        
        StackPane stack = new StackPane();
        stack.getChildren().addAll(new Rectangle(primaryScreenBounds.getWidth() / 1.8, primaryScreenBounds.getHeight() / 1.8, Color.OLIVEDRAB), hBox);
        
        Scene scene = new Scene(stack, primaryScreenBounds.getWidth() / 1.8, primaryScreenBounds.getHeight() / 1.8);

        scene.setCursor(Cursor.OPEN_HAND); //Pourra être remplacé par un curseur personnalisé

        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        
        primaryStage.show();
        primaryStage.toFront();        
        
        /*Thread taskThread = new Thread(new Runnable() {
            @Override
            public void run() { 
            	simulation.launch();
            	while (simulation.isRunning()) {
        			SimulationUtility.unitTime();
        			simulation.update();
        			dashboard.drawShapes();
            	}
        			Platform.runLater(new Runnable() {
        				@Override
        				public void run() {
        				}
        			});
        		}
        	});
        	taskThread.start(); 
    	} */
        
    }
        
  
    
   
     public static void main(String[] args) {
    	 launch(args);
     } 
}