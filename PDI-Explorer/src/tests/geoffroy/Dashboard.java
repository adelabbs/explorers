package tests.geoffroy;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import GUI.PaintVisitor;
import data.entity.Entity;
import data.entity.LivingEntity;
import data.simulation.Environment;

public class Dashboard extends JPanel {

	private static final long serialVersionUID = 1L;

	public static final int TILE_SIZE = 10;

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		printMap(g);
		printLivingEntities(g);
	}

	private void printMap(Graphics g) {
		super.paintComponent(g);
		PaintVisitor pv = new PaintVisitor(g);
		
		for (int i = 0; i < 90; i++) {
			for (int j = 0; j < 90; j++) {
				switch (Environment.getInstance().getMap().getTile(i, j).getType()) {
				case "w":
					g.setColor(Color.BLUE);
					break;
				case "g":
					g.setColor(Color.LIGHT_GRAY);
					break;
				}
				g.fillRect(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
			}
		}
		for(Entity entity : Environment.getInstance().getEntities())
			entity.accept(pv);
		for(Entity entity : Environment.getInstance().getObstacles())
			entity.accept(pv);
		
		g.dispose();
		
	}

	private void printLivingEntities(Graphics g) {
		Environment e = Environment.getInstance();
		for (LivingEntity livingEntity : e.getEntities()) {
			PaintVisitor paintVisitor = new PaintVisitor(g);
			livingEntity.accept(paintVisitor);
		}
	}

}
