package tests.geoffroy;

import java.awt.Graphics;

import javax.swing.JPanel;

import data.entity.Entity;
import data.entity.LivingEntity;
import data.simulation.Environment;
import gui.MapPainter;
import gui.PaintVisitor;

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
		MapPainter mp = new MapPainter(g);
		
		mp.paint();
		
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
