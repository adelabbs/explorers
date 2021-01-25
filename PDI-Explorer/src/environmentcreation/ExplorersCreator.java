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

		ArrayList<Explorer> explorers = new ArrayList<>();
		explorers.add(new Explorer(new double[] {5.0, 6.0}, 15, 8, 3, 3, "Roger", 
				new ExplorerMap(new ExplorerTile[90][90]), 5));
		explorers.add(new Explorer(new double[] {7.0, 5.0}, 15, 8, 3, 3, "Michel", 
				new ExplorerMap(new ExplorerTile[90][90]), 5));
		explorers.add(new Explorer(new double[] {3.0, 2.0}, 15, 8, 3, 3, "Didier", 
				new ExplorerMap(new ExplorerTile[90][90]), 5));
		return explorers;
	}
	
}
