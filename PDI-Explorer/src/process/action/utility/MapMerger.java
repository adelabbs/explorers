package process.action.utility;

import data.map.ExplorerTile;
import data.simulation.Environment;

/**
 * 
 * This utility class is used to merge an explorer's map with the general map
 *
 */
public class MapMerger {

	public static void merge(ExplorerTile[][] map) {
		ExplorerTile[][] gem = Environment.getInstance().getGeneralExplorerMap().getTiles();
		mergeMaps(map, gem);
		mergeMaps(gem, map);
	}
	
	/**
	 * 
	 * @param em1
	 * @param em2
	 */
	private static void mergeMaps(ExplorerTile[][] em1, ExplorerTile[][] em2) {
		for(int i = 0; i < 90; i ++) {
			for(int j = 0; j < 90; j ++) {
				if(em1[i][j].isExplored() && !em2[i][j].isExplored()) {
					em2[i][j].setExplored(true);
					em2[i][j].setInterest(em1[i][j].getInterest());
					em2[i][j].setType(em1[i][j].getType());
					em2[i][j].setVisible(true);
				}
			}
		}
	}
}
