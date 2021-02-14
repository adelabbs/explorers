package tests.lucas;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import data.simulation.Item;
import data.simulation.SimulationEntry;
import process.itemCreation.ItemFactory;

public class Menu2 extends JPanel implements ActionListener, ItemListener{
	
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
	JPanel health = new JPanel();
	JPanel speed = new JPanel();
	JPanel damage = new JPanel();
	JPanel scope = new JPanel();
	JPanel com = new JPanel();
	
	
	private String[] nbExplorers = { "3", "4", "5", "6" };
	private SimulationEntry sim = new SimulationEntry(0,0,10,0);
	int animalAmount;
	
	public Menu2 () {
		this.setLayout(new FlowLayout());
		
		health.setLayout(new BoxLayout(health, BoxLayout.Y_AXIS));
		speed.setLayout(new BoxLayout(speed, BoxLayout.Y_AXIS));
		damage.setLayout(new BoxLayout(damage, BoxLayout.Y_AXIS));
		scope.setLayout(new BoxLayout(scope, BoxLayout.Y_AXIS));
		com.setLayout(new BoxLayout(com, BoxLayout.Y_AXIS));
		
		JComboBox explorerChoice = new JComboBox(nbExplorers);
		explorerChoice.setSelectedIndex(0);
		explorerChoice.addActionListener(this);
		
		this.add(explorerChoice);

		Binoculars.setSelected(false);
	    Knife.setSelected(false);
	    Antenna.setSelected(false);
	    Glasses.setSelected(false);
	    Machete.setSelected(false);
	    Radio.setSelected(false);
	    Helmet.setSelected(false);
	    Trecking_Shoes.setSelected(false);
	    Chestplate.setSelected(false);
	    Boots.setSelected(false);

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
	    
	    health.add(Helmet);
	    health.add(Chestplate);
	    speed.add(Boots);
	    speed.add(Trecking_Shoes);
	    damage.add(Knife);
	    damage.add(Machete);
	    scope.add(Glasses);
	    scope.add(Binoculars);
	    com.add(Antenna);
	    com.add(Radio);
	    
	    this.add(health);
	    this.add(speed);
	    this.add(damage);
	    this.add(scope);
	    this.add(com);
	    
	    ButtonGroup healthGroup = new ButtonGroup();
	    healthGroup.add(Helmet);
	    healthGroup.add(Chestplate);

	    ButtonGroup speedGroup = new ButtonGroup();
	    speedGroup.add(Boots);
	    speedGroup.add(Trecking_Shoes);
	    
	    ButtonGroup damageGroup = new ButtonGroup();
	    damageGroup.add(Knife);
	    damageGroup.add(Machete);
	    
	    ButtonGroup scopeGroup = new ButtonGroup();
	    scopeGroup.add(Glasses);
	    scopeGroup.add(Binoculars);
	    
	    ButtonGroup comGroup = new ButtonGroup();
	    comGroup.add(Antenna);
	    comGroup.add(Radio);
	    
	    animalAmount = (int) (2 + (Math.random() * (6 - 2)));
	    
	    sim.setAnimalAmount(animalAmount);
	}
	    
	    public void itemStateChanged(ItemEvent e) {

	    	Object source = e.getItemSelectable();

	    	if (e.getStateChange() == ItemEvent.SELECTED) {
	    		if (source == Binoculars) {
	    			sim.add("Binoculars");
	    		} else if (source == Knife) {
	    			sim.add("Knife");
	    		} else if (source == Antenna) {
	    			sim.add("Antenna");
	    		} else if (source == Glasses) {
	    			sim.add("Glasses");
	    		} else if (source == Helmet) {
	    			sim.add("Helmet");
	    		} else if (source == Chestplate) {
	    			sim.add("Chestplate");
	    		} else if (source == Boots) {
	    			sim.add("Boots");
	    		} else if (source == Trecking_Shoes) {
	    			sim.add("Trecking_Shoes");
	    		} else if (source == Machete) {
	    			sim.add("Machete");
	    		} else if (source == Radio) {
	    			sim.add("Radio");
	    		}
	    	}
	    }

	public SimulationEntry getSim() {
			return sim;
		}

	public void actionPerformed(ActionEvent e) { 
		JComboBox cb = (JComboBox)e.getSource();
        String nbExplorers = (String)cb.getSelectedItem();
		sim.setExplorerAmount(Integer.valueOf(nbExplorers));
	} 
}

