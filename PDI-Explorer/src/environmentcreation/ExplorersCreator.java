package environmentcreation;

import java.util.ArrayList;

import data.entity.Explorer;
import data.map.ExplorerMap;
import data.map.ExplorerTile;

/**
 * Creation of the Explorer ArrayList, depending on the amount of explorer asked by the user.
 * 
 * @author Léo COQUET
 *
 */

public class ExplorersCreator {

	public static ArrayList<Explorer> creation(int explorerAmount){

		ExplorerPlacer ep = new ExplorerPlacer(explorerAmount);
		
		ArrayList<Explorer> explorers = new ArrayList<>();
		for(int i = 0; i < explorerAmount; i ++)
		explorers.add(new Explorer(ep.place(), 15, 8, 3, 3, "Roger", 
				new ExplorerMap(new ExplorerTile[90][90]), 5));
//		explorers.add(new Explorer(ep.place(), 15, 8, 3, 3, "Roger", 
//				new ExplorerMap(new ExplorerTile[90][90]), 5));
//		explorers.add(new Explorer(ep.place(), 15, 8, 3, 3, "Michel", 
//				new ExplorerMap(new ExplorerTile[90][90]), 5));
//		explorers.add(new Explorer(ep.place(), 15, 8, 3, 3, "Didier", 
//				new ExplorerMap(new ExplorerTile[90][90]), 5));
		return explorers;
		
	}
	
}
