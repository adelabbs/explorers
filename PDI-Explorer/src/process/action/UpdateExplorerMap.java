package process.action;

import data.entity.Entity;
import data.entity.Explorer;
import data.entity.LivingEntity;
import data.map.ExplorerTile;
import data.map.Tile;
import data.simulation.Environment;
import process.SimulationUtility;
/**
 * 
 * Update the map of an explorer (not displayed on the GUI)
 *
 */
public class UpdateExplorerMap {

	/**
	 * Update of the ExplorerMap
	 * @param e
	 */
	public static void update(Explorer e) {
		int originI = (int) e.getPosition()[0];
		int originJ = (int) e.getPosition()[1];
		for(int i = originI - e.getScope(); i < originI + e.getScope(); i ++) {
			for(int j = originJ- e.getScope(); j < originJ + e.getScope(); j ++) {
				int distance = (int) SimulationUtility.distance(new double[] {(double) i, (double) j}, 
						e.getPosition());
				if(distance < e.getScope())
					if(i >= 0 && j >= 0 && i < 90 && j < 90)
						updateTile(i, j, e);
			}
		}
	}
	
	/**
	 * Verifies the Tile's informations and set the good values on the ExplorerTile
	 * @param i
	 * @param j
	 * @param e
	 */
	private static void updateTile(int i, int j, Explorer e) {
		ExplorerTile explorerTile = e.getMap().getTile(i, j);
		Tile tile = Environment.getInstance().getMap().getTile(i, j);
		if(!explorerTile.isExplored()) {
			explorerTile.setType(tile.getType());
			explorerTile.setExplored(true);
			for(LivingEntity le : Environment.getInstance().getEntities())
				if(le.getType().equals("Bear") && (int) le.getPosition()[0] == i && (int) le.getPosition()[1] == j)
					explorerTile.setInterest(-1);
			for(Entity entity : Environment.getInstance().getObstacles())
				if(entity.getType().equals("Chest") && (int) entity.getPosition()[0] == i && (int) entity.getPosition()[1] == j)
					explorerTile.setInterest(1);
		}
	}
	
}
