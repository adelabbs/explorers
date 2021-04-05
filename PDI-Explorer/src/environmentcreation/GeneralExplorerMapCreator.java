package environmentcreation;

import data.map.ExplorerTile;
import data.map.GeneralExplorerMap;

/**
 * Creates the general explorer map, we can see it as the minimap during simulation.
 * 
 * @author Léo
 *
 */
public class GeneralExplorerMapCreator {

	/**
	 * Creation of the map.
	 * 
	 * @return The map.
	 */
	public static GeneralExplorerMap creation() {
		GeneralExplorerMap gem = new GeneralExplorerMap();
		ExplorerTile[][] tiles = gem.getTiles();
		for(int i = 0; i < 90; i ++) {
			for(int j = 0; j < 90; j ++) {
				tiles[i][j] = new ExplorerTile();
			}
		}
		return gem;
	}
	
}
