package tests.leo;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import data.entity.Entity;
import data.simulation.Environment;
import environmentcreation.EnvironmentCreator;
import environmentcreation.event.EntityCreationException;
import gui.MapPainter;
import gui.PaintVisitor;

@SuppressWarnings("serial")
public class TestImages extends JPanel {
	
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
		MapPainter mp = new MapPainter(g);
		
		mp.paint();
		
		for(Entity entity : Environment.getInstance().getEntities())
			entity.accept(pv);
		for(Entity entity : Environment.getInstance().getObstacles())
			entity.accept(pv);
		
		g.dispose();
	}
	
	public static void main(String[] args) {
		try {
			EnvironmentCreator.creation(3, 0, 10);
		} catch (EntityCreationException e) {
			e.printStackTrace();
		}
		TestImages test = new TestImages();
		for(Entity e : Environment.getInstance().getEntities())
			System.out.println(e);
	}

}
