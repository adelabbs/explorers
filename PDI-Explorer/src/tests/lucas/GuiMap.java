package tests.lucas;

import java.awt.Color;

import java.awt.Graphics;
import javax.swing.JPanel;

import data.entity.Entity;
import data.entity.Explorer;
import data.simulation.Environment;
import environmentcreation.EnvironmentCreator;
import environmentcreation.event.EntityCreationException;
import gui.PaintVisitor;


@SuppressWarnings("serial")
public class GuiMap extends JPanel {

	private static final int TILE_SIZE = 11;
		
	public GuiMap() throws EntityCreationException {
		EnvironmentCreator.creation(3, 0, 0);
	}
		
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		PaintVisitor pv = new PaintVisitor(g);
		
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
}