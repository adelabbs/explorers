package environmentcreation.mapcreation;

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

		SubMap[][] subMaps = new SubMap[6][6];
		CornerGenerator.generation(subMaps);
		BorderGenerator.generation(subMaps);
		SubMapsFiller.fill(subMaps);
		char[][] zones = ZonesGenerator.generation(subMaps);
		Tile[][] tiles = TilesGenerator.generation(zones);		
		return new Map(tiles);
		
	}
	
}
