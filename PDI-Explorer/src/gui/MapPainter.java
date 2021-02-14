package gui;

import java.awt.Color;
import java.awt.Graphics;

import data.entity.Explorer;
import data.map.ExplorerMap;
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
		explorerMapsPainter();
	}
	
	private void explorerMapsPainter() {
		int initY = 90*Dashboard.TILE_SIZE;
		int entityAmount = Environment.getInstance().getEntities().size();
		int sent = 0;
		for(int i = 0; i < entityAmount; i ++)
			if(Environment.getInstance().getEntities().get(i).getType().equals("Explorer")) {
				explorerMapPainter((sent/2)*90*3, initY + (sent%2)*90*3, i);
				sent ++;
			}
	}
	
	private void explorerMapPainter(int x, int y, int i) {
		ExplorerMap map = ((Explorer) Environment.getInstance().getEntities().get(i)).getMap();
		for(int k = 0; k < 90; k ++)
			for(int j = 0; j < 90; j ++) {
				if(map.getTile(k, j).isExplored()) {
					if(map.getTile(k, j).getInterest() == 0) {
						switch(map.getTile(k, j).getType()) {
						case "g" :
							g.setColor(Color.LIGHT_GRAY);
							break;
						case "w" :
							g.setColor(Color.BLUE);
							break;
						}
					} else {
						switch(map.getTile(i, j).getInterest()) {
						case 1 :
							g.setColor(Color.YELLOW);
							break;
						case -1 :
							g.setColor(Color.RED);
						}
					}
				} else {
					g.setColor(Color.BLACK);
				}
				g.fillRect(y + j*3, x + k*3, 3, 3);
			}
//		g.setColor(Color.LIGHT_GRAY);
//		g.fillRect(y, x, 90, 90);
	}
	
}
