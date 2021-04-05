package environmentcreation;

import java.util.ArrayList;

import data.entity.LivingEntity;
import environmentcreation.event.EntityCreationException;

/**
 * Creates the entities.
 * 
 * @author Léo
 *
 */
public class EntityCreator {

	/**
	 * Calls all the functions to create explorers and animal.
	 * 
	 * @param explorerAmount The amount of explorers.
	 * @param animalAmount The amount of animal.
	 * @return The ArrayList of LivingEntities.
	 * @throws EntityCreationException
	 */
	public static ArrayList<LivingEntity> creation(int explorerAmount, int animalAmount) throws EntityCreationException{
		
		ArrayList<LivingEntity> al = new ArrayList<LivingEntity>();
		al.addAll(ExplorersCreator.creation(explorerAmount));
		al.addAll(AnimalsCreator.creation(animalAmount));
		return al;
		
	}
	
}
