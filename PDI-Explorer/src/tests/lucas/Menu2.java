package tests.lucas;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import data.simulation.SimulationEntry;

@SuppressWarnings("serial")
public class Menu2 extends JPanel implements ActionListener, ItemListener{
	
	JRadioButton noTools = new JRadioButton("No tools");
	JRadioButton bareHands = new JRadioButton("Bare Hands");
	JRadioButton noGlasses = new JRadioButton("No Glasses");
	JRadioButton simpleShirt = new JRadioButton("Simple Shirt");
	JRadioButton normalShoes = new JRadioButton("Normal Shoes");
	JRadioButton Binoculars = new JRadioButton("Binoculars");
	JRadioButton Glasses = new JRadioButton("Glasses");
	JRadioButton Helmet = new JRadioButton("Helmet");
	JRadioButton Chestplate = new JRadioButton("Chestplate");
	JRadioButton Boots = new JRadioButton("Boots");
	JRadioButton Trecking_Shoes = new JRadioButton("Trecking Shoes");
	JRadioButton Knife = new JRadioButton("Knife");
	JRadioButton Machete = new JRadioButton("Machete");
	JRadioButton Antenna = new JRadioButton("Antenna");
	JRadioButton Radio = new JRadioButton("Radio");
	
	JPanel globalEnvelope = new JPanel();
	JPanel explorers = new JPanel();
	JPanel health = new JPanel();
	JPanel speed = new JPanel();
	JPanel damage = new JPanel();
	JPanel scope = new JPanel();
	JPanel com = new JPanel();
	
	JButton play = new JButton("Play");
	
	
	private String[] nbExplorers = { "3", "4", "5", "6" };
	private SimulationEntry sim = new SimulationEntry(0,0,10,0);
	private int animalAmount;
	private int envelope = 180;
	private String explorersAmount = "3";
	
	private JTextField envelopeField = new JTextField(String.valueOf(envelope) ,3);
	
	public Menu2 () {
		this.setLayout(new FlowLayout());
		
		globalEnvelope.setLayout(new BoxLayout(globalEnvelope, BoxLayout.Y_AXIS));
		explorers.setLayout(new BoxLayout(explorers, BoxLayout.Y_AXIS));
		health.setLayout(new BoxLayout(health, BoxLayout.Y_AXIS));
		speed.setLayout(new BoxLayout(speed, BoxLayout.Y_AXIS));
		damage.setLayout(new BoxLayout(damage, BoxLayout.Y_AXIS));
		scope.setLayout(new BoxLayout(scope, BoxLayout.Y_AXIS));
		com.setLayout(new BoxLayout(com, BoxLayout.Y_AXIS));
		
		JComboBox explorerChoice = new JComboBox(nbExplorers);
		explorerChoice.setSelectedIndex(0);
		explorerChoice.addActionListener(this);
		
		JLabel labelExplorers = new JLabel("Number of explorers:");
		
		JLabel gEnveloppe = new JLabel("Money amount :");
		envelopeField.setEditable(false);
		
		globalEnvelope.add(gEnveloppe);
		globalEnvelope.add(envelopeField);
		
		explorers.add(labelExplorers);
		explorers.add(explorerChoice);
		
		this.add(globalEnvelope);
		this.add(explorers);

		noTools.setSelected(true);
	    bareHands.setSelected(true);
	    simpleShirt.setSelected(true);
	    normalShoes.setSelected(true);
	    noGlasses.setSelected(true);

	    noTools.addItemListener(this);
	    bareHands.addItemListener(this);
	    simpleShirt.addItemListener(this);
	    noGlasses.addItemListener(this);
	    normalShoes.addItemListener(this);
	    Binoculars.addItemListener(this);
	    Knife.addItemListener(this);
	    Antenna.addItemListener(this);
	    Glasses.addItemListener(this);
	    Machete.addItemListener(this);
	    Radio.addItemListener(this);
	    Helmet.addItemListener(this);
	    Trecking_Shoes.addItemListener(this);
	    Chestplate.addItemListener(this);
	    Boots.addItemListener(this);
	    
	    health.add(simpleShirt);
	    health.add(Helmet);
	    health.add(Chestplate);
	    
	    speed.add(normalShoes);
	    speed.add(Boots);
	    speed.add(Trecking_Shoes);
	    
	    damage.add(bareHands);
	    damage.add(Knife);
	    damage.add(Machete);
	    
	    scope.add(noGlasses);
	    scope.add(Glasses);
	    scope.add(Binoculars);
	    
	    com.add(noTools);
	    com.add(Antenna);
	    com.add(Radio);
	    
	    this.add(health);
	    this.add(speed);
	    this.add(damage);
	    this.add(scope);
	    this.add(com);
	    
	    ButtonGroup healthGroup = new ButtonGroup();
	    healthGroup.add(simpleShirt);
	    healthGroup.add(Helmet);
	    healthGroup.add(Chestplate);

	    ButtonGroup speedGroup = new ButtonGroup();
	    speedGroup.add(normalShoes);
	    speedGroup.add(Boots);
	    speedGroup.add(Trecking_Shoes);
	    
	    ButtonGroup damageGroup = new ButtonGroup();
	    damageGroup.add(bareHands);
	    damageGroup.add(Knife);
	    damageGroup.add(Machete);
	    
	    ButtonGroup scopeGroup = new ButtonGroup();
	    scopeGroup.add(noGlasses);
	    scopeGroup.add(Glasses);
	    scopeGroup.add(Binoculars);
	    
	    ButtonGroup comGroup = new ButtonGroup();
	    comGroup.add(noTools);
	    comGroup.add(Antenna);
	    comGroup.add(Radio);
	    
	    animalAmount = (int) (2 + (Math.random() * (6 - 2)));
	    
	    sim.setAnimalAmount(animalAmount);
	}
	    
	    @SuppressWarnings("static-access")
		public void itemStateChanged(ItemEvent e) {

	    	Object source = e.getItemSelectable();
	    	if (envelope <= 0) {
	    		JOptionPane.showMessageDialog(new Frame(), "You went over budget.", "Warning", JOptionPane.WARNING_MESSAGE);
	    		sim.getItems().clear();
	    		envelope = 180;
	    		noTools.setSelected(true);
	    	    bareHands.setSelected(true);
	    	    simpleShirt.setSelected(true);
	    	    normalShoes.setSelected(true);
	    	    noGlasses.setSelected(true);
	    	}
	    	else {
	    		if (e.getStateChange() == ItemEvent.SELECTED) {
	    			if (source == Binoculars) {
	    				envelope -= 10* Integer.valueOf(explorersAmount);
	    				sim.add("Binoculars");
	    			} else if (source == Knife) {
	    				envelope -= 5* Integer.valueOf(explorersAmount);
	    				sim.add("Knife");
	    			} else if (source == Antenna) {
	    				envelope -= 5* Integer.valueOf(explorersAmount);
	    				sim.add("Antenna");
	    			} else if (source == Glasses) {
	    				envelope -= 5* Integer.valueOf(explorersAmount);
	    				sim.add("Glasses");
	    			} else if (source == Helmet) {
	    				envelope -= 5* Integer.valueOf(explorersAmount);
	    				sim.add("Helmet");
	    			} else if (source == Chestplate) {
	    				envelope -= 10* Integer.valueOf(explorersAmount);
	    				sim.add("Chestplate");
	    			} else if (source == Boots) {
	    				envelope -= 5* Integer.valueOf(explorersAmount);
	    				sim.add("Boots");
	    			} else if (source == Trecking_Shoes) {
	    				envelope -= 10* Integer.valueOf(explorersAmount);
	    				sim.add("Trecking_Shoes");
	    			} else if (source == Machete) {
	    				envelope -= 10* Integer.valueOf(explorersAmount);
	    				sim.add("Machete");
	    			} else if (source == Radio) {
	    				envelope -= 10* Integer.valueOf(explorersAmount);
	    				sim.add("Radio");
	    			}	
	    		}
	    		else if (e.getStateChange() == ItemEvent.DESELECTED) {
	    			if (source == Binoculars) {
	    				envelope += 10* Integer.valueOf(explorersAmount);
	    			} else if (source == Knife) {
	    				envelope += 5* Integer.valueOf(explorersAmount);
	    			} else if (source == Antenna) {
	    				envelope += 5* Integer.valueOf(explorersAmount);
	    			} else if (source == Glasses) {
	    				envelope += 5* Integer.valueOf(explorersAmount);
	    			} else if (source == Helmet) {
	    				envelope += 5* Integer.valueOf(explorersAmount);
	    			} else if (source == Chestplate) {
	    				envelope += 10* Integer.valueOf(explorersAmount);
	    			} else if (source == Boots) {
	    				envelope += 5* Integer.valueOf(explorersAmount);
	    			} else if (source == Trecking_Shoes) {
	    				envelope += 10* Integer.valueOf(explorersAmount);
	    			} else if (source == Machete) {
	    				envelope += 10* Integer.valueOf(explorersAmount);
	    			} else if (source == Radio) {
	    				envelope += 10* Integer.valueOf(explorersAmount);
	    			}  		
	    		}
	    	}
	    	envelopeField.setText(String.valueOf(envelope));
	    	System.out.println(envelope);
	    }
	

	public SimulationEntry getSim() {
			return sim;
		}

	@SuppressWarnings("rawtypes")
	public void actionPerformed(ActionEvent e) { 
		JComboBox cb = (JComboBox)e.getSource();
        explorersAmount = (String)cb.getSelectedItem();
		sim.setExplorerAmount(Integer.valueOf(explorersAmount));
	} 
}

