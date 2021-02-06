package environmentcreation;

import java.util.ArrayList;

import data.entity.LivingEntity;
import environmentcreation.event.EntityCreationException;

public class EntityCreator {

	public static ArrayList<LivingEntity> creation(int explorerAmount, int animalAmount, int chestAmount) throws EntityCreationException{
		
		ArrayList<LivingEntity> al = new ArrayList<LivingEntity>();
		al.addAll(ExplorersCreator.creation(explorerAmount));
		al.addAll(AnimalsCreator.creation(animalAmount));
		return al;
		
	}
	
}
