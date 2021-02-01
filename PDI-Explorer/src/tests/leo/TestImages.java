package tests.leo;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import data.entity.Explorer;
import data.simulation.Environment;
import environmentcreation.EnvironmentCreator;
import environmentcreation.event.EntityCreationException;

public class TestImages extends JPanel {

	private static final int TILE_SIZE = 10;
	private static Environment environment;
	
	public TestImages() {
		
		JFrame frame = new JFrame("Guardians");
		frame.add(this);
		frame.setSize(1920,1080);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i = 0; i < 90; i ++) {
			for(int j = 0; j < 90; j ++) {
				switch(environment.getMap().getTile(i, j).getType()) {
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
		
		g.setColor(Color.DARK_GRAY);
		for(int x = 0; x < environment.getExplorerAmount(); x ++) {
			Explorer e = environment.getExplorers().get(x);
			int i = (int) e.getPosition()[0];
			int j = (int) e.getPosition()[1];
			g.fillRect(j*TILE_SIZE, i*TILE_SIZE, TILE_SIZE, TILE_SIZE);
		}
		
		g.dispose();
	}
	
	public static void main(String[] args) {
		try {
			environment = EnvironmentCreator.creation(3, 0, 0);
		} catch (EntityCreationException e) {
			e.printStackTrace();
		}
		TestImages test = new TestImages();
	}

}
