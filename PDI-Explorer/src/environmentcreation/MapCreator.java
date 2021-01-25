package environmentcreation;

import data.map.Map;
import data.map.Tile;

/**
 * g is a ground tile
 * w is a water tile
 * 
 * 90*90 map, which can be cut as 6*6 sub maps of 15*15 tiles.
 * Each sub map can be cut as 3*3 zones of 5*5 tiles.
 * Each zone is exclusively composed, on its border, by ground or water tile.
 * No matter what the center tiles are.
 * each sub map has a code based on how are placed its zones.
 * Ex :
 *     7
 *	 g g g
 * 5 w g w 4	from left to right or from top to bottom.
 *	 g g w
 *     6 
 * 
 * This sub map has the code 7465.
 * For a binary code, g is 1 and w is 0.
 * so, g g g => 1 1 1 = 7.
 *  
 * @author Léo COQUET
 *
 */

public class MapCreator {

	public static Map creation() {
		
		Tile[][] tiles = new Tile[90][90];
		
		for(int i = 0; i < 90; i ++)
			for(int j = 0; j < 90; j ++)
				if(i == 0 || i == 1 || i == 88 || i == 89)
					tiles[i][j] = new Tile("w");
				else if(j == 0 || j == 1 || j == 88 || j == 89)
					tiles[i][j] = new Tile("w");
				else
					tiles[i][j] = new Tile("g");
		return new Map(tiles);
	}
	
}
