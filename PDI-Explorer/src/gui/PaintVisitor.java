package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import data.entity.Bear;
import data.entity.Chest;
import data.entity.Entity;
import data.entity.Explorer;
import data.entity.Obstacle;
import process.visitor.EntityVisitor;
import tests.geoffroy.Dashboard;

public class PaintVisitor implements EntityVisitor<Void> {

	private static final int IMG_TILE_SIZE = 25;
	
	private Graphics g;
	private Image tiles;

	public PaintVisitor(Graphics g) {
		this.g = g;
		try {
            tiles = ImageIO.read(new File("ressources/img/texture.png"));
        } catch (IOException e) {
            e.printStackTrace();
        } 
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
		int r = 2 * entity.getScope();
		int x = (int) entity.getPosition()[1] - r / 2;
		int y = (int) entity.getPosition()[0] - r / 2;
		r *= Dashboard.TILE_SIZE;
		x = x * Dashboard.TILE_SIZE + Dashboard.TILE_SIZE / 2;
		y = y * Dashboard.TILE_SIZE + Dashboard.TILE_SIZE / 2;
		g.drawOval(x, y, r, r);
		//paint(entity);
		int i = (int) entity.getPosition()[0];
		int j = (int) entity.getPosition()[1];
		x=0;
		y=0;
		g.drawImage(tiles, j*Dashboard.TILE_SIZE, i*Dashboard.TILE_SIZE, 
				j*Dashboard.TILE_SIZE+Dashboard.TILE_SIZE, i*Dashboard.TILE_SIZE+Dashboard.TILE_SIZE, 
				x, y, x+IMG_TILE_SIZE, y+IMG_TILE_SIZE, null);
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
		g.fillRect(j * Dashboard.TILE_SIZE, i * Dashboard.TILE_SIZE, (int) (Dashboard.TILE_SIZE * entity.getSize()[0]),
				(int) (Dashboard.TILE_SIZE * entity.getSize()[1]));
	}

}
