package gui;

import java.io.FileInputStream;
import java.io.IOException;
import data.map.ExplorerTile;
import data.simulation.Environment;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * 
 * @author lespi
 *
 */
public class MapPainterFX {

	private static double IMG_TILE_SIZE = 25;
	
	private GraphicsContext g;
	private Image tiles;
	
	/**
	 * 
	 * @param gc
	 */
	public MapPainterFX(GraphicsContext gc){
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
				g.drawImage(tiles, x , y, IMG_TILE_SIZE, IMG_TILE_SIZE, j*MapFX.TILE_SIZE, i*MapFX.TILE_SIZE, MapFX.TILE_SIZE, MapFX.TILE_SIZE);
			}
		}
		generalMapPainter();
	}
	
	public void generalMapPainter() {
		ExplorerTile[][] map = Environment.getInstance().getGeneralExplorerMap().getTiles();
		int originY = (int) (MapFX.height - MapFX.height/3.17);
		int originX = (int) (MapFX.width - MapFX.width/5.24);
		
		for(int i = 0; i < 90; i ++) {
			for(int j = 0; j < 90; j ++) {
				if(map[i][j].isExplored()) {
					if(map[i][j].getType().equals("g"))
						g.setFill(Color.GRAY);
					else
						g.setFill(Color.BLUE);
				}
				else
					g.setFill(Color.BLACK);
				g.fillRect(originX + j*3, originY + i*3, 3, 3);
			}
		}
	}
	
}