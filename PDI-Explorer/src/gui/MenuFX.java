package gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import process.factory.ManagerFactory;

/**
 * 
 * @author lespi
 *
 */

public class MenuFX extends Application {
	
	private Stage primaryStage;
	private int enveloppe = 180;
	private Text text2 = new Text(String.valueOf(enveloppe));
	
	private Button startButton = new Button("START");
	private VBox enveloppeBox = new VBox();
	private VBox leftBox = new VBox();
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
    
    private ImageView healthView = new ImageView();
    private ImageView speedView = new ImageView();
    private ImageView scopeView = new ImageView();
    private ImageView damageView = new ImageView();
    private ImageView comView = new ImageView();
    private ArrayList<ImageView> views = new ArrayList<ImageView>();

    private ToggleGroup comGroup = new ToggleGroup();
    private ToggleGroup scopeGroup = new ToggleGroup();
    private ToggleGroup damageGroup = new ToggleGroup();
    private ToggleGroup speedGroup = new ToggleGroup();
    private ToggleGroup healthGroup = new ToggleGroup();
    
    private Text healthEffect = new Text("None");
    private Text speedEffect = new Text("None");
    private Text damageEffect = new Text("None");
    private Text scopeEffect = new Text("None");
    private Text comEffect = new Text("None");
    private ArrayList<Text> effects = new ArrayList<Text>();
    
    private VBox buttonBox1 = new VBox(healthButton1, healthButton2, healthButton3);
    private VBox buttonBox2 = new VBox(speedButton1, speedButton2, speedButton3);
    private VBox buttonBox3 = new VBox(damageButton1, damageButton2, damageButton3);
    private VBox buttonBox4 = new VBox(scopeButton1, scopeButton2, scopeButton3);
    private VBox buttonBox5 = new VBox(comButton1, comButton2, comButton3);
    
    private VBox effectBox1 = new VBox(healthEffect, healthView);
    private VBox effectBox2 = new VBox(speedEffect, speedView);
    private VBox effectBox3 = new VBox(damageEffect, damageView);
    private VBox effectBox4 = new VBox(scopeEffect, scopeView);
    private VBox effectBox5 = new VBox(comEffect, comView);
    
    private VBox healthBox = new VBox(buttonBox1 , effectBox1);
    private VBox speedBox = new VBox(buttonBox2 , effectBox2);
    private VBox damageBox = new VBox(buttonBox3 , effectBox3);
    private VBox scopeBox = new VBox(buttonBox4 , effectBox4);
    private VBox comBox = new VBox(buttonBox5 , effectBox5);
    
    private Tab health = new Tab("Health", healthBox);
    private Tab speed = new Tab("Speed", speedBox);
    private Tab damage = new Tab("Damage", damageBox);
    private Tab scope = new Tab("Scope", scopeBox);
    private Tab com = new Tab("Communication Range", comBox);
    
    private Image image;
    private Image image1;
    private Image image2;
    private Image image3;
    private Image image4;
    private Image image5;
    private Image image6;
    private Image image7;
    private Image image8;
    private Image image9;
    private Image image10;
    private Image image11;
	 
	private int healthTemp = 0;
	private int speedTemp = 0;
	private int damageTemp = 0;
	private int scopeTemp = 0;
	private int comTemp = 0;
	private int stratNbr;
	
	ChoiceBox<String> stratChoice = new ChoiceBox<String>();
	
	private final Spinner<Integer> nbExplorers = new Spinner<Integer>();   
    private final int initialNb = 3;
    private SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(3, 6, initialNb);
    private VBox leftButtons = new VBox(nbExplorers, stratChoice, startButton);

	private static int EXPLORATION_STRATEGY = ManagerFactory.REGION_STRATEGY;
	
	private HashMap<String, String> items = new HashMap<String, String>();	
	private Rectangle2D primaryScreenBounds;

    @Override
    public void start(Stage primaryStage)  {
    	try {
			init(primaryStage);
			handlers(primaryStage);
			simLaunch();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 
     * @param primaryStage
     * @throws FileNotFoundException
     */
	private void init(Stage stage) throws FileNotFoundException {
        primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    	
        this.primaryStage = stage;
        primaryStage.setTitle("Autonomous and communicant explorers");        
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setFullScreen(true);
        primaryStage.setResizable(false);
        
        text2.setFont(Font.font("Arial", FontWeight.BOLD, primaryScreenBounds.getHeight() / 10));
        text2.setFill(new Color(0.90, 0.68, 0.06, 1.0));
        text2.setStroke(new Color(0.68, 0.48, 0.16, 1.0));
        text2.setTextOrigin(VPos.CENTER);
        
        effects.add(healthEffect);
        effects.add(speedEffect);
        effects.add(damageEffect);
        effects.add(scopeEffect);
        effects.add(comEffect);
        
        for (Text t2 : effects) {
        	t2.setFont(Font.font("Arial", FontWeight.BOLD, primaryScreenBounds.getHeight() / 25));
            t2.setFill(new Color(0.68, 0.48, 0.26, 1.0));
            t2.setTextOrigin(VPos.CENTER);
        }
        
        FileInputStream input = new FileInputStream("ressources/img/Essai_explorateur.gif");
        image = new Image(input);
        ImageView explorerGIF = new ImageView(image);
        explorerGIF.setFitHeight(primaryScreenBounds.getHeight() / 5);
        explorerGIF.setPreserveRatio(true);
        
        FileInputStream input1 = new FileInputStream("ressources/img/aucun_objet.png");
        image1 = new Image(input1);
        
        views.add(healthView);
        views.add(speedView);
        views.add(damageView);
        views.add(scopeView);
        views.add(comView);
        
        for (ImageView v : views) {
        	v.setImage(image1);
            v.setFitHeight(primaryScreenBounds.getHeight() / 5);
            v.setPreserveRatio(true);
        }
        
        FileInputStream input2 = new FileInputStream("ressources/img/objects/helmet.png");
		image2 = new Image(input2);
		FileInputStream input3 = new FileInputStream("ressources/img/objects/chestplate.png");
		image3 = new Image(input3);
		
		FileInputStream input4 = new FileInputStream("ressources/img/objects/boots.png");
		image4 = new Image(input4);
		FileInputStream input5 = new FileInputStream("ressources/img/objects/trecking_shoes.png");
		image5 = new Image(input5);
		
		FileInputStream input6 = new FileInputStream("ressources/img/objects/knife.png");
		image6 = new Image(input6);
		FileInputStream input7 = new FileInputStream("ressources/img/objects/machete.png");
		image7 = new Image(input7);
		
		FileInputStream input8 = new FileInputStream("ressources/img/objects/glasses.png");
		image8 = new Image(input8);
		FileInputStream input9 = new FileInputStream("ressources/img/objects/binoculars.png");
		image9 = new Image(input9);
		
		FileInputStream input10 = new FileInputStream("ressources/img/objects/antenna.png");
		image10 = new Image(input10);
		FileInputStream input11 = new FileInputStream("ressources/img/objects/radio.png");
		image11 = new Image(input11);
  
        nbExplorers.setValueFactory(valueFactory);
        
        enveloppeBox.setAlignment(Pos.TOP_CENTER);
        enveloppeBox.setSpacing(primaryScreenBounds.getHeight() / 8);
        enveloppeBox.getChildren().addAll(text2, explorerGIF);
        
        stratChoice.getItems().add("All rounder strategy");
        stratChoice.getItems().add("Greed strategy");
        stratChoice.getItems().add("Reckless strategy");
        stratChoice.getItems().add("Dodge strategy");
        stratChoice.getItems().add("Priority to the exploration");
        stratChoice.getItems().add("Running strategy");
        
        leftButtons.setAlignment(Pos.TOP_CENTER);
        leftButtons.setSpacing(primaryScreenBounds.getHeight() / 20);
        
        leftBox.getChildren().addAll(enveloppeBox, leftButtons);
        leftBox.setAlignment(Pos.TOP_CENTER);
        leftBox.setSpacing(primaryScreenBounds.getHeight() / 20);
        leftBox.setPadding(new Insets(primaryScreenBounds.getHeight() / 12, 0, 0, 0));
        
        healthButton1.setToggleGroup(healthGroup);
        healthButton2.setToggleGroup(healthGroup);
        healthButton3.setToggleGroup(healthGroup);            
        healthBox.setAlignment(Pos.TOP_CENTER);
        
        speedButton1.setToggleGroup(speedGroup);
        speedButton2.setToggleGroup(speedGroup);
        speedButton3.setToggleGroup(speedGroup);      
        speedBox.setAlignment(Pos.TOP_CENTER);

        damageButton1.setToggleGroup(damageGroup);
        damageButton2.setToggleGroup(damageGroup);
        damageButton3.setToggleGroup(damageGroup);      
        damageBox.setAlignment(Pos.TOP_CENTER);
        
        scopeButton1.setToggleGroup(scopeGroup);
        scopeButton2.setToggleGroup(scopeGroup);
        scopeButton3.setToggleGroup(scopeGroup);       
        scopeBox.setAlignment(Pos.TOP_CENTER);

        comButton1.setToggleGroup(comGroup);
        comButton2.setToggleGroup(comGroup);
        comButton3.setToggleGroup(comGroup);      
        comBox.setAlignment(Pos.TOP_CENTER);
        
        healthButton1.setSelected(true);
        speedButton1.setSelected(true);
        damageButton1.setSelected(true);
        scopeButton1.setSelected(true);
        comButton1.setSelected(true);
        
        stratChoice.getSelectionModel().selectFirst();
        
        buttonBox1.setAlignment(Pos.TOP_CENTER);
        buttonBox1.setSpacing(primaryScreenBounds.getHeight() / 15);
        effectBox1.setAlignment(Pos.BASELINE_CENTER);
        effectBox1.setSpacing(primaryScreenBounds.getHeight() / 12);
        
        buttonBox2.setAlignment(Pos.TOP_CENTER);
        buttonBox2.setSpacing(primaryScreenBounds.getHeight() / 15);
        effectBox2.setAlignment(Pos.BASELINE_CENTER);
        effectBox2.setSpacing(primaryScreenBounds.getHeight() / 12);
        
        buttonBox3.setAlignment(Pos.TOP_CENTER);
        buttonBox3.setSpacing(primaryScreenBounds.getHeight() / 15);
        effectBox3.setAlignment(Pos.BASELINE_CENTER);
        effectBox3.setSpacing(primaryScreenBounds.getHeight() / 12);
        
        buttonBox4.setAlignment(Pos.TOP_CENTER);
        buttonBox4.setSpacing(primaryScreenBounds.getHeight() / 15);
        effectBox4.setAlignment(Pos.BASELINE_CENTER);
        effectBox4.setSpacing(primaryScreenBounds.getHeight() / 12);
        
        buttonBox5.setAlignment(Pos.TOP_CENTER);
        buttonBox5.setSpacing(primaryScreenBounds.getHeight() / 15);
        effectBox5.setAlignment(Pos.BASELINE_CENTER);
        effectBox5.setSpacing(primaryScreenBounds.getHeight() / 12);

        healthBox.setPadding(new Insets(primaryScreenBounds.getHeight() / 15, 0, 0, 0));
        healthBox.setSpacing(primaryScreenBounds.getHeight() / 6.3);
        speedBox.setPadding(new Insets(primaryScreenBounds.getHeight() / 15, 0, 0, 0));
        speedBox.setSpacing(primaryScreenBounds.getHeight() / 6.3);
        damageBox.setPadding(new Insets(primaryScreenBounds.getHeight() / 15, 0, 0, 0));
        damageBox.setSpacing(primaryScreenBounds.getHeight() / 6.3);
        scopeBox.setPadding(new Insets(primaryScreenBounds.getHeight() / 15, 0, 0, 0));
        scopeBox.setSpacing(primaryScreenBounds.getHeight() / 6.3);
        comBox.setPadding(new Insets(primaryScreenBounds.getHeight() / 15, 0, 0, 0));
        comBox.setSpacing(primaryScreenBounds.getHeight() / 6.3);

        tabPane.getTabs().add(health);
        tabPane.getTabs().add(speed);
        tabPane.getTabs().add(damage);
        tabPane.getTabs().add(scope);
        tabPane.getTabs().add(com); 
        
        HBox hBox = new HBox(leftBox, tabPane);
        hBox.setPadding(new Insets(primaryScreenBounds.getHeight() / 12, primaryScreenBounds.getWidth() / 15, primaryScreenBounds.getHeight() / 15, primaryScreenBounds.getWidth() / 4.7));
        hBox.setSpacing(primaryScreenBounds.getWidth() / 2.83);      
        
        FileInputStream inputM = new FileInputStream("ressources/img/menu.png");
        Image imageM = new Image(inputM);
        ImageView background = new ImageView(imageM);
        background.setFitWidth(primaryScreenBounds.getWidth());
        background.setPreserveRatio(true);
        
        StackPane stack = new StackPane();
        stack.getChildren().addAll(background, hBox);
        
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
    
    /**
     * 
     * @param primaryStage
     */
    
    @SuppressWarnings({"unused"})
	public void handlers(Stage primaryStage) {	
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
						healthView.setImage(image2);     	        
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
            			healthView.setImage(image3);
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
					healthView.setImage(image1);
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
            			speedView.setImage(image4);
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
            			speedView.setImage(image5);
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
            		speedView.setImage(image1);
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
            			damageView.setImage(image6);
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
            			damageView.setImage(image7);
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
            		damageView.setImage(image1);
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
            			scopeView.setImage(image8);
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
            			scopeView.setImage(image9);
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
            		scopeView.setImage(image1);
            	}
            	text2.setText(String.valueOf(enveloppe));
            }
        });
        comGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov, Toggle toggle,Toggle new_toggle) {
            	if (comButton1.isSelected() == false) {
            		comBox.setSpacing(primaryScreenBounds.getHeight() / 10);
            		if (comButton2.isSelected() == true) {
            			items.put("com", "Antenna");
            			if (comTemp == 0) {
            				enveloppe -= 5*nbExplorers.getValue();
            			}
            			else if (comTemp == 2) {
            				enveloppe += 5*nbExplorers.getValue();
            			}
            			comTemp = 1;
            			comEffect.setText("Communication \n Range +");
            			comView.setImage(image10);
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
            			comEffect.setText("Communication \n Range ++");
            			comView.setImage(image11);
            		}
        		}
            	else if (comButton1.isSelected() == true) {
            		comBox.setSpacing(primaryScreenBounds.getHeight() / 6.3);
            		items.remove("com");
            		if (comTemp == 1) {
        				enveloppe += 5*nbExplorers.getValue();
        			}
        			else if (comTemp == 2) {
        				enveloppe += 10*nbExplorers.getValue();
        			}
            		comTemp = 0;
            		comEffect.setText("None");
            		comView.setImage(image1);
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
    }
    
    private void simLaunch() {
    	stratChoice.setOnAction((event) -> {
    	    stratNbr = stratChoice.getSelectionModel().getSelectedIndex();
    	    @SuppressWarnings("unused")
    		SimulationFX sim = new SimulationFX(primaryStage, startButton, enveloppe, valueFactory, EXPLORATION_STRATEGY, items, primaryScreenBounds, views, stratNbr);
    	});
    }         
   
    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
    	launch(args);
    } 
}