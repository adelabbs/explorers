package tests.leo;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import GUI.PaintVisitor;
import data.entity.Entity;
import data.simulation.Environment;
import environmentcreation.EnvironmentCreator;
import environmentcreation.event.EntityCreationException;

@SuppressWarnings("serial")
public class TestImages extends JPanel {

	private static final int TILE_SIZE = 11;
	
	public TestImages() {
		
		JFrame frame = new JFrame("Explorers");
		frame.add(this);
		frame.setSize(1920,1080);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		PaintVisitor pv = new PaintVisitor(g);
		
		//faire fonction plus jolie externe
		for(int i = 0; i < 90; i ++) {
			for(int j = 0; j < 90; j ++) {
				switch(Environment.getInstance().getMap().getTile(i, j).getType()) {
				case "w" :
					g.setColor(Color.BLUE);
					break;
				case "g" :
					g.setColor(Color.LIGHT_GRAY);
					break;
				}
				g.fillRect(j*TILE_SIZE, i*TILE_SIZE, TILE_SIZE, TILE_SIZE);
			}
		}
		
		for(Entity entity : Environment.getInstance().getEntities())
			entity.accept(pv);
		
		g.dispose();
	}
	
	public static void main(String[] args) {
		try {
			EnvironmentCreator.creation(3, 0, 0);
		} catch (EntityCreationException e) {
			e.printStackTrace();
		}
		TestImages test = new TestImages();
	}

}
