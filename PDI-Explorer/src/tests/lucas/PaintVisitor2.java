package tests.lucas;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import data.entity.Bear;
import data.entity.Chest;
import data.entity.Entity;
import data.entity.Explorer;
import data.entity.LivingEntity;
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

	private Image tree;
	private Image bigTree;
	private Image rock;

	private Image chest;

	public PaintVisitor2(GraphicsContext gc) {
		this.g = gc;
		try {
			FileInputStream input = new FileInputStream("ressources/img/texture.png");
			this.tiles = new Image(input);

			input = new FileInputStream("ressources/img/obstacles/tree.png");
			this.tree = new Image(input);

			input = new FileInputStream("ressources/img/obstacles/bigtree.png");
			this.bigTree = new Image(input);

			input = new FileInputStream("ressources/img/obstacles/rock.png");
			this.rock = new Image(input);

			input = new FileInputStream("ressources/img/obstacles/treasure (1).png");
			this.chest = new Image(input);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Void visit(Chest entity) {
		int i = (int) entity.getPosition()[0];
		int j = (int) entity.getPosition()[1];

		g.drawImage(chest, 0, 0, IMG_TILE_SIZE, IMG_TILE_SIZE, j * DashboardFX.TILE_SIZE, i * DashboardFX.TILE_SIZE,
				DashboardFX.TILE_SIZE, DashboardFX.TILE_SIZE);
		return null;
	}

	@Override
	public Void visit(Obstacle entity) {
		int i = (int) entity.getPosition()[0];
		int j = (int) entity.getPosition()[1];

		
		if (entity.getType().equals("rock:23")) {
			g.drawImage(rock, 0, 0, 2*IMG_TILE_SIZE, 3*IMG_TILE_SIZE, j * DashboardFX.TILE_SIZE, i * DashboardFX.TILE_SIZE,
					2*DashboardFX.TILE_SIZE, 3*DashboardFX.TILE_SIZE);		
		}
		else if (entity.getType().equals("tree:12")) {
			g.drawImage(tree, 0, 0, IMG_TILE_SIZE, 2*IMG_TILE_SIZE, j * DashboardFX.TILE_SIZE, i * DashboardFX.TILE_SIZE,
					DashboardFX.TILE_SIZE, 2*DashboardFX.TILE_SIZE);	
		} else {
			g.drawImage(bigTree, 0, 0, 2*IMG_TILE_SIZE, 2*IMG_TILE_SIZE, j * DashboardFX.TILE_SIZE, i * DashboardFX.TILE_SIZE,
					2*DashboardFX.TILE_SIZE, 2*DashboardFX.TILE_SIZE);	
		}
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
		x = 0;
		y = 0;
		g.drawImage(tiles, x, y, IMG_TILE_SIZE, IMG_TILE_SIZE, j * DashboardFX.TILE_SIZE, i * DashboardFX.TILE_SIZE,
				DashboardFX.TILE_SIZE, DashboardFX.TILE_SIZE);
		paintLifeBar(entity);
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
		g.fillRect(j * DashboardFX.TILE_SIZE, i * DashboardFX.TILE_SIZE,
				(int) (DashboardFX.TILE_SIZE * entity.getSize()[0]),
				(int) (DashboardFX.TILE_SIZE * entity.getSize()[1]));
	}

	private void paintLifeBar(LivingEntity entity) {
		int i = (int) entity.getPosition()[0];
		int j = (int) entity.getPosition()[1];
		int half = (int) (IMG_TILE_SIZE / (2 * scale));
		g.setFill(Color.RED);
		g.fillRect(j * DashboardFX.TILE_SIZE - half, i * DashboardFX.TILE_SIZE - half, (DashboardFX.TILE_SIZE) * 2, 3);
		g.setFill(Color.LIGHTGREEN);
		g.fillRect(j * DashboardFX.TILE_SIZE - half, i * DashboardFX.TILE_SIZE - half,
				(int) ((DashboardFX.TILE_SIZE) * 2 * (entity.getHealth() / entity.getMaxHealth())), 3);
	}

}