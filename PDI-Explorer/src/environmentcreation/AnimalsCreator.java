package environmentcreation;

import java.util.ArrayList;

import data.entity.LivingEntity;

/**
 * Creation of the Animals ArrayList, depending on the amount of animals asked by the user.
 * 
 * @author Léo COQUET
 *
 */

public class AnimalsCreator {

	public static ArrayList<LivingEntity> creation(int animalAmount){
		
		ArrayList<LivingEntity> animals = new ArrayList<>();
		animals.add(new LivingEntity("Bear", new double[]{75.2, 52.0}, new double[]{2.0, 1.0}, 25, 5, 20, 3));
		animals.add(new LivingEntity("Bear", new double[]{95, 93.2}, new double[]{2.0, 1.0}, 25, 5, 20, 3));
		animals.add(new LivingEntity("Bear", new double[]{23.2, 18.0}, new double[]{2.0, 1.0}, 25, 5, 20, 3));
		return animals;
		
	}
	
}
