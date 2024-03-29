package environmentcreation;

import java.util.ArrayList;

import data.entity.Bear;
import data.entity.LivingEntity;
import data.simulation.Environment;

/**
 * Creation of the Animals ArrayList, depending on the amount of animals asked by the user.
 * 
 * @author L�o COQUET
 *
 */

public class AnimalsCreator {

	/**
	 * Creation of the 
	 * 
	 * @param animalAmount The amount of animal wanted.
	 * @return An ArrayList of Animal.
	 */
	public static ArrayList<LivingEntity> creation(int animalAmount){
		
		ArrayList<LivingEntity> animals = new ArrayList<>();
		for(int i = 0; i < animalAmount; i ++) {
			animals.add(new Bear(positioning()));
		}
		return animals;
		
	}
	
	private static double[] positioning() {
		double[] pos = new double[2];
		do {
			pos[0] = Math.random()*90;
			pos[1] = Math.random()*90;
		} while(Environment.getInstance().getMap().getTile((int) pos[0], (int) pos[1]).getType().equals("w"));
		return pos;
	}
	
}
