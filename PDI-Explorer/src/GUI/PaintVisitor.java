package GUI;

import java.awt.Color;
import java.awt.Graphics;

import data.entity.Bear;
import data.entity.Chest;
import data.entity.Entity;
import data.entity.Explorer;
import data.entity.Obstacle;
import process.visitor.EntityVisitor;
import tests.manual.Dashboard;

public class PaintVisitor implements EntityVisitor<Void> {
	private Graphics g;

	public PaintVisitor(Graphics g) {
		this.g = g;
	}

	@Override
	public Void visit(Chest entity) {
		g.setColor(Color.YELLOW);
		paint(entity);
		return null;
	}

	@Override
	public Void visit(Obstacle entity) {
		g.setColor(Color.GREEN);
		paint(entity);
		return null;
	}

	@Override
	public Void visit(Explorer entity) {
		g.setColor(Color.DARK_GRAY);
		paint(entity);
		return null;
	}

	@Override
	public Void visit(Bear entity) {
		g.setColor(Color.RED);
		paint(entity);
		return null;
	}
	
	private void paint(Entity entity) {
		int i = (int) entity.getPosition()[0];
		int j = (int) entity.getPosition()[1];
		g.fillRect(j * Dashboard.TILE_SIZE, i * Dashboard.TILE_SIZE, (int) (Dashboard.TILE_SIZE * entity.getSize()[0]), (int) (Dashboard.TILE_SIZE* entity.getSize()[1]));
	}
	
}
