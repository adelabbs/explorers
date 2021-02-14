package tests.lucas;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import data.simulation.Environment;
import data.simulation.SimulationEntry;
import environmentcreation.event.EntityCreationException;
import process.Simulation;
import process.SimulationUtility;
import tests.geoffroy.move_actions.Travel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

@SuppressWarnings("serial")
public class GUI2 extends JFrame implements ActionListener {
	private static CardLayout card = new CardLayout(10, 10);
	private static Container c;
	private Menu2 menu = new Menu2();
	JButton start;
	
	public GUI2() throws IOException {
		super();
		this.setTitle("Explorers");
		
		c = getContentPane();
	    c.setLayout(card);
	    this.setSize(1040, 1040);
	    this.setVisible(true);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    start = new JButton("Start");     
	    start.addActionListener(this);
	    
	    c.add(start);
	    c.add(menu);
	}
	
	public void actionPerformed(ActionEvent e) {  
	    card.next(c); 
	}  
	
}
