package environmentcreation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

		String line = null;
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("ressources/names.csv"));
			line = reader.readLine();
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String[] names = line.split(",");
		
		ExplorerPlacer ep = new ExplorerPlacer(explorerAmount);
		
		ArrayList<Explorer> explorers = new ArrayList<>();
		for(int i = 0; i < explorerAmount; i ++)
			explorers.add(new Explorer(ep.place(), names[i]));
		return explorers;
		
	}
	
}
