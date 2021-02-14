package GUI;

import java.awt.Color;
import java.awt.Graphics;

import data.simulation.Environment;
import tests.geoffroy.Dashboard;

public class MapPainter {

	private Graphics g;
	
	public MapPainter(Graphics g){
		this.g = g;
	}
	
	public void paint() {
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
				g.fillRect(j*Dashboard.TILE_SIZE, i*Dashboard.TILE_SIZE, Dashboard.TILE_SIZE, Dashboard.TILE_SIZE);
			}
		}
	}
	
}
