package tests.lucas;

import java.io.FileInputStream;
import java.io.IOException;

import data.entity.Bear;
import data.entity.Chest;
import data.entity.Entity;
import data.entity.Explorer;
import data.entity.Obstacle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import process.visitor.EntityVisitor;

public class PaintVisitor2 implements EntityVisitor<Void> {

	private static double IMG_TILE_SIZE = 25;
	private double scale = 2.5;
	
	private GraphicsContext g;
	private Image tiles;

	public PaintVisitor2(GraphicsContext gc) {
		this.g = gc;
		try {
            FileInputStream input = new FileInputStream("ressources/img/texture.png");
            this.tiles = new Image(input);
        } catch (IOException e) {
            e.printStackTrace();
        } 
	}

	@Override
	public Void visit(Chest entity) {
		g.setFill(Color.YELLOW);
		paint(entity);
		return null;
	}

	@Override
	public Void visit(Obstacle entity) {
		g.setFill(Color.GREEN);
		paint(entity);
		return null;
	}

	@Override
	public Void visit(Explorer entity) {
		g.setFill(Color.DARKGRAY);
		int r = 2 * entity.getScope();
		double x = entity.getPosition()[1] - r / 2;
		double y = entity.getPosition()[0] - r / 2;
		r *= DashboardFX.TILE_SIZE;
		x = x * DashboardFX.TILE_SIZE + DashboardFX.TILE_SIZE / 2;
		y = y * DashboardFX.TILE_SIZE + DashboardFX.TILE_SIZE / 2;
		g.strokeOval(x, y, r, r);
		int i = (int) entity.getPosition()[0];
		int j = (int) entity.getPosition()[1];
		x=0;
		y=0;
		g.drawImage(tiles, x, y, IMG_TILE_SIZE, IMG_TILE_SIZE, j*IMG_TILE_SIZE/scale, i*IMG_TILE_SIZE/scale, IMG_TILE_SIZE/scale, IMG_TILE_SIZE/scale);
		return null;
	}

	@Override
	public Void visit(Bear entity) {
		g.setFill(Color.RED);
		paint(entity);
		return null;
	}

	private void paint(Entity entity) {
		int i = (int) entity.getPosition()[0];
		int j = (int) entity.getPosition()[1];
		g.fillRect(j * DashboardFX.TILE_SIZE, i * DashboardFX.TILE_SIZE, (int) (DashboardFX.TILE_SIZE * entity.getSize()[0]),
				(int) (DashboardFX.TILE_SIZE * entity.getSize()[1]));
	}

}