package environmentcreation;

import java.util.ArrayList;

import data.entity.Entity;
import environmentcreation.event.EntityCreationException;

public class EntityCreator {

	public static ArrayList<Entity> creation(int explorerAmount, int animalAmount, int chestAmount) throws EntityCreationException{
		
		ArrayList<Entity> al = new ArrayList<Entity>();
		al.addAll(ExplorersCreator.creation(explorerAmount));
		al.addAll(AnimalsCreator.creation(animalAmount));
		al.addAll(ChestsCreator.creation(chestAmount));
		al.addAll(ObstaclesCreator.creation());
		
		return al;
		
	}
	
}
