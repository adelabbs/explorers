package move_actions;

import data.entity.Animal;
import data.entity.Entity;
import data.entity.LivingEntity;
import data.simulation.Environment;

/*
 * This class is used to set all explorers movement for the current tick
 */

public class Travel {

	//MIN & MAX for directions change MAX to 8 for diagonals
	private static final int MIN = 1;
	private static final int MAX = 4; 
	
	// Four possible directions
	private static final int NORTH = 1;
	private static final int EAST = 2;
	private static final int SOUTH = 3;
	private static final int WEST = 4;
	
	// Territory's borders for all animals
	private static final double ANIMAL_MAX_TERRITORY = 20;

	//Absolute border for square map
	private static int MAP_BORDER = 90;

	private double nextPos[] = null;

	public Travel() {}
	
	// This method iterate the ArrayList of explorers to set new position for each explorers
	public void determineAllNewPositions() {
		for(LivingEntity e : Environment.getInstance().getEntities()) {
			randomMovement(e);
		}
	}
	
	// This method is used to know if the entity's destination is free or not out of border
	public boolean checkForCollide(double[] nextPosition) {
		for(Entity e : Environment.getInstance().getObstacles()) {
			if(e.getPosition() == nextPosition) {
				return false;
			}
		}
		if(nextPosition[0] == Math.abs(MAP_BORDER) || 
				(nextPosition[1] == Math.abs(MAP_BORDER))){
			return false;
			
		}
		return true;
	}
	
	// Random movement for explorers (only 4 directions) this method will be the same for animals
	public void randomMovement(LivingEntity entity) {
		int direction = (int) (MIN + Math.random()*((MAX-MIN) + 1));
		nextPos = entity.getPosition();
		
		switch(direction) {
		case NORTH:
			System.out.println("NORTH");
			nextPos[1]++;
			break;
		case EAST:
			System.out.println("EAST");
			nextPos[0]++;
			break;
		case WEST:
			nextPos[0]--;
			System.out.println("WEST");
			break;
		case SOUTH:
			nextPos[1]--;
			System.out.println("SOUTH");
			break;
		}
		
		//If free :
		if(checkForCollide(nextPos)) {
			//Set the position for the next tick
			entity.setPosition(nextPos);
		}
		//Or check for a new position
		randomMovement(entity);
	}
	
	/*
	 * This method is used to determine the movement of an animal
	 * Two cases : 
	 * 1) The animal is on the border -> The animal move back
	 * 2) The animal is inside his territory -> Random Movement
	 */
	public void moveAnimal(Animal animal) {
		double[] nextPosition = animal.getPosition();
		//If the animal is located on his territory's border :
		if(Math.abs(nextPosition[0]) == ANIMAL_MAX_TERRITORY || 
				Math.abs(nextPosition[1]) == ANIMAL_MAX_TERRITORY) {
			// axe X 
			if(nextPosition[0] > 0) {
				nextPosition[0]--;
			} else {
				nextPosition[0]++;
			}
			// axe Y
			if(nextPosition[1] > 0) {
				nextPosition[1]--;
			} else {
				nextPosition[1]++;
			}
			//Set new location
			animal.setPosition(nextPosition);
		}
		//If not, random movement :
		else {
			randomMovement(animal);
		}
	}
	
}
