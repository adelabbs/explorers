package environmentcreation;

import java.util.ArrayList;

import data.entity.Chest;

public class ChestsCreator {

	public static ArrayList<Chest> creation(int chestAmount){
		
		ArrayList<Chest> chests = new ArrayList<>();
		chests.add(new Chest(new double[] {25.0, 25.0}));
		chests.add(new Chest(new double[] {33.0, 51.0}));
		chests.add(new Chest(new double[] {96.0, 90.0}));
		return chests;
		
	}
	
}
