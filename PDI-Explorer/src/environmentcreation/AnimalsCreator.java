package environmentcreation;

import java.util.ArrayList;

import data.entity.Bear;
import data.entity.LivingEntity;
import data.simulation.Environment;

/**
 * Creation of the Animals ArrayList, depending on the amount of animals asked by the user.
 * 
 * @author Léo COQUET
 *
 */

public class AnimalsCreator {

	public static ArrayList<LivingEntity> creation(int animalAmount){
		
		ArrayList<LivingEntity> animals = new ArrayList<>();
		animals.add(new Bear(positioning()));
		animals.add(new Bear(positioning()));
		animals.add(new Bear(positioning()));
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
