package tests.lucas;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import data.simulation.Environment;
import data.simulation.SimulationEntry;
import environmentcreation.event.EntityCreationException;
import move_actions.Travel;
import process.Simulation;
import process.SimulationUtility;

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
	JButton jb1, jb2, jb3, jb4, jb5, jb6;
	private JPanel parameters = new JPanel();
	private JPanel simulation = new JPanel();
	private SideBar sideBar;
	
	public GUI2(GuiMap sim) throws IOException {
		super();
		this.setTitle("Explorers");
		
		c = getContentPane();
	    c.setLayout(card);
	    this.setSize(940, 940);
	    this.setVisible(true);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    jb1 = new JButton("Start");      
	    jb2 = new JButton("Option 1");
	    jb3 = new JButton("Option 2");
	    jb4 = new JButton("Option 3");      
	    jb5 = new JButton("Option 4");
	    jb6 = new JButton("Option 5"); 
	        
	    jb1.addActionListener(this);
	    jb2.addActionListener(this);
	    jb3.addActionListener(this);
	    jb4.addActionListener(this);
	    jb5.addActionListener(this);
	    jb6.addActionListener(this);
	    
	    parameters.add(jb2);
	    parameters.add(jb3);
	    parameters.add(jb4);
	    parameters.add(jb5);
	    parameters.add(jb6);
	    
	    c.add(jb1);
	    c.add(parameters);
	    c.add(simulation);
	    simulation.setLayout(new BorderLayout());
	    simulation.add(BorderLayout.CENTER, sim);
	    simulation.add(BorderLayout.EAST, sideBar);
	   	//c.add(sim);
	}
	
	public void actionPerformed(ActionEvent e) {  
	    card.next(c); 
	}  
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		try {
			int x = 1000;
			GuiMap sim = new GuiMap();
			GUI2 test = new GUI2(sim);
			/*Travel travel = Travel(Environment.getInstance());
			while (x > 0) {
				
			}*/
		} catch (EntityCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
