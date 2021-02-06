package GUI;

import java.awt.Color;
import java.awt.Graphics;

import data.entity.Bear;
import data.entity.Chest;
import data.entity.Explorer;
import data.entity.Obstacle;
import process.EntityVisitor;

public class PaintVisitor implements EntityVisitor<Void> {
	private Graphics graphics;

	public PaintVisitor(Graphics graphics) {
		this.graphics = graphics;
	}

	@Override
	public Void visit(Chest entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(Obstacle entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visit(Explorer entity) {
		graphics.setColor(Color.DARK_GRAY);
		int i = (int) entity.getPosition()[0];
		int j = (int) entity.getPosition()[1];
		graphics.fillRect(j * Dashboard.TILE_SIZE, i * Dashboard.TILE_SIZE, Dashboard.TILE_SIZE, Dashboard.TILE_SIZE);
		return null;
	}

	@Override
	public Void visit(Bear entity) {
		graphics.setColor(Color.RED);
		int i = (int) entity.getPosition()[0];
		int j = (int) entity.getPosition()[1];
		graphics.fillRect(j * Dashboard.TILE_SIZE, i * Dashboard.TILE_SIZE, Dashboard.TILE_SIZE, Dashboard.TILE_SIZE);
		return null;
	}
}
