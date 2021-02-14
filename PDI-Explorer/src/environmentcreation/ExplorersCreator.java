package environmentcreation;

import java.util.ArrayList;

import data.entity.Explorer;

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
		explorers.add(new Explorer(ep.place(), "Roger"));
		return explorers;
		
	}
	
}
