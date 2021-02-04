package move_actions;

import java.util.ArrayList;

import data.entity.Entity;
import data.entity.Explorer;
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
	
	
	private Environment environment = null;
	private ArrayList<Explorer> explorers = null;
	private ArrayList<Entity> obstacles = null;

	private double nextPos[] = null;

	public Travel(Environment environment) {
		this.environment = environment;
		explorers = environment.getExplorers();
		obstacles = environment.getObstacles();
	}
	
	// This method iterate the ArrayList of explorers to set new position for each explorers
	public void determineAllNewPositions() {
		for(Explorer e : explorers) {
			randomMovement(e);
		}
		updatePosition();
	}
	
	// This method is used to know if the entity's destination is free
	public boolean checkForCollide(double[] nextPosition) {
		for(Entity e : obstacles) {
			if(e.getPosition() == nextPosition) {
				return false;
			}
		}
		return true;
	}
	
	// Random movement for explorers (only 4 directions) this method will be the same for animals
	public void randomMovement(Explorer explorer) {
		int direction = (int) (MIN + Math.random()*((MAX-MIN) + 1));
		nextPos = explorer.getPosition();
		
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
			explorer.setPosition(nextPos);
		}
		//Or check for a new position
		randomMovement(explorer);
	}
	
	public void updatePosition() {
		environment.setExplorers(explorers);
	}
}
