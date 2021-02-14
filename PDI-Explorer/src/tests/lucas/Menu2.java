package tests.lucas;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import data.simulation.Item;
import data.simulation.SimulationEntry;
import process.itemCreation.ItemFactory;

public class Menu2 extends JPanel implements ActionListener, ItemListener{
	
	private JCheckBox Binoculars = new JCheckBox("Binoculars");
	private JCheckBox Knife = new JCheckBox("Knife");
	private JCheckBox Antenna = new JCheckBox("Antenna");
	
	private String[] nbExplorers = { "3", "4", "5", "6" };
	private SimulationEntry sim = new SimulationEntry(0,0,10,0);
	int animalAmount;
	
	public Menu2 () {
		this.setLayout(new FlowLayout());
		
		JComboBox explorerChoice = new JComboBox(nbExplorers);
		explorerChoice.setSelectedIndex(0);
		explorerChoice.addActionListener(this);
		
		this.add(explorerChoice);

		Binoculars.setMnemonic(KeyEvent.VK_C); 
		Binoculars.setSelected(false);

	    Knife.setMnemonic(KeyEvent.VK_G); 
	    Knife.setSelected(false);

	    Antenna.setMnemonic(KeyEvent.VK_H); 
	    Antenna.setSelected(false);

	    Binoculars.addItemListener(this);
	    Knife.addItemListener(this);
	    Antenna.addItemListener(this); 
	    
	    this.add(Binoculars);
	    this.add(Knife);
	    this.add(Antenna);
	    
	    animalAmount = (int) (2 + (Math.random() * (6 - 2)));
	    
	    sim.setAnimalAmount(animalAmount);
	}
	    
	    public void itemStateChanged(ItemEvent e) {

	    	Object source = e.getItemSelectable();

	    	if (e.getStateChange() == ItemEvent.SELECTED) {
	    		if (source == Binoculars) {
	    			//Item binoculars = ItemFactory.create("Binoculars");
	    			sim.add("Binoculars");
	    		} else if (source == Knife) {
	    			//Item knife = ItemFactory.create("Knife");
	    			sim.add("Knife");
	    		} else if (source == Antenna) {
	    			//Item antenna = ItemFactory.create("Antenna");
	    			sim.add("Antenna");
	    		}
	    	}
	    	else if (e.getStateChange() == ItemEvent.DESELECTED) {
	    		if (source == Binoculars) {
	    			sim.getItems().remove("Binoculars");
		    	} else if (source == Knife) {
		    		sim.getItems().remove("Knife");
		    	} else if (source == Antenna) {
		    		sim.getItems().remove("Antenna");
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

