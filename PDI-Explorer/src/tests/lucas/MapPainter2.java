package tests.lucas;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import data.entity.Explorer;
import data.map.ExplorerMap;
import data.simulation.Environment;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import tests.geoffroy.Dashboard;

public class MapPainter2 {

	private static double IMG_TILE_SIZE = 25;
	private double scale = 2.5;
	
	private GraphicsContext g;
	private Image tiles;
	
	public MapPainter2(GraphicsContext gc){
		this.g = gc;
		try {
			FileInputStream input = new FileInputStream("ressources/img/tiles.png");
            tiles = new Image(input);
        } catch (IOException e) {
            e.printStackTrace();
        } 
	}
	
	public void paint() {
		int x = 0;
		int y = 0;
		for(int i = 0; i < 90; i ++) {
			for(int j = 0; j < 90; j ++) {
				switch(Environment.getInstance().getMap().getTile(i, j).getType()) { 
				case "w" :
					x = (int) IMG_TILE_SIZE;
					y = 0;
					break;
				case "g" :
					x = 0;
					y = 0;
					break;
				}
				g.drawImage(tiles, x , y, IMG_TILE_SIZE, IMG_TILE_SIZE, j*DashboardFX.TILE_SIZE, i*DashboardFX.TILE_SIZE, DashboardFX.TILE_SIZE, DashboardFX.TILE_SIZE);
			}
		}
		//explorerMapsPainter();
	}
	
	private void explorerMapsPainter() {
		int initY = (int) (90*DashboardFX.TILE_SIZE);
		int entityAmount = Environment.getInstance().getEntities().size();
		int sent = 0;
		for(int i = 0; i < entityAmount; i ++)
			if(Environment.getInstance().getEntities().get(i).getType().equals("Explorer")) {
				explorerMapPainter((sent/2)*90*3, initY + (sent%2)*90*3, i);
				sent ++;
			}
	}
	
	private void explorerMapPainter(int x, double d, int i) {
		ExplorerMap map = ((Explorer) Environment.getInstance().getEntities().get(i)).getMap();
		for(int k = 0; k < 90; k ++)
			for(int j = 0; j < 90; j ++) {
				if(map.getTile(k, j).isExplored()) {
					if(map.getTile(k, j).getInterest() == 0) {
						switch(map.getTile(k, j).getType()) {
						case "g" :
							g.setFill(Color.LIGHTGRAY);
							break;
						case "w" :
							g.setFill(Color.BLUE);
							break;
						}
					} else {
						switch(map.getTile(i, j).getInterest()) {
						case 1 :
							g.setFill(Color.YELLOW);
							break;
						case -1 :
							g.setFill(Color.RED);
						}
					}
				} else {
					g.setFill(Color.BLACK);
				}
				g.fillRect(d + j*3, x + k*3, 3, 3);
			}
//		g.setColor(Color.LIGHT_GRAY);
//		g.fillRect(y, x, 90, 90);
	}
	
}