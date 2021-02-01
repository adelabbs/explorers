package move_actions;

import data.entity.Explorer;
import data.map.ExplorerMap;

/*
 * This class is used to set the explorer's movement for the current tick
 * 
 */

public class Travel {

	private Explorer explorer;

	private static final int MIN = 1;
	private static final int MAX = 4;
	
	private static final int NORTH = 1;
	private static final int EAST = 2;
	private static final int SOUTH = 3;
	private static final int WEST = 4;
	
	double nextPos[] = null;
	double currentPos[] = null;
	
	public Travel(Explorer explorer) {
		this.explorer = explorer;
		currentPos = explorer.getPosition();
	}
	
	// Random movement for explorers (only 4 directions) this method will be the same for animals
	public void randomMovement() {
		int direction = (int) (MIN + Math.random()*((MAX-MIN) + 1));
		nextPos = currentPos;
		
		switch(direction) {
		case NORTH:
			System.out.println("NORTH");
			nextPos[1] ++;
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
		//Set the position for the next tick
		explorer.setPosition(nextPos);
		
		updatePosition();
		resetNextPosition();
	}
	
	public void updatePosition() {
		currentPos = explorer.getPosition();
	}
	
	public void resetNextPosition() {
		nextPos = null;
	}
	
	public void testMovement() {
		System.out.println("BEGINNING EXPLORER'S POSITION : " + currentPos[0] + ", " + currentPos[1]);
		for(int i = 0; i < 10; i++) {
			randomMovement();
			System.out.println("EXPLORER'S POSITION : " + currentPos[0] + ", " + currentPos[1]);
		}
	}
	
	
	public Explorer getExplorer() {
		return explorer;
	}
	
	public void setExplorer(Explorer explorer) {
		this.explorer = explorer;
	}
	
	
	// Test main 
	public final static void main(String[] args) {
		double[] pos = {1,2};
		ExplorerMap exmap = new ExplorerMap(null);
		Explorer ex = new Explorer(pos, 100,5,5,5,"hi", exmap, 5);
		Travel travel = new Travel(ex);
		travel.testMovement();
	}

}
