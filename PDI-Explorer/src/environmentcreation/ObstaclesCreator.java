package environmentcreation;

import java.util.ArrayList;

import data.entity.Entity;
import environmentcreation.event.EntityCreationException;

/**
 * Class used to create the obstacles.
 * 
 * @author Léo
 *
 */
public class ObstaclesCreator {
	
	/**
	 * Creates the obstacles, trees, rocks and chests.
	 * 
	 * @param chestAmount The amount of chests.
	 * @return The ArrayList of Obstacles.
	 * @throws EntityCreationException
	 */
	public static ArrayList<Entity> creation(int chestAmount) throws EntityCreationException {
		
		ArrayList<Entity> obstacles = new ArrayList<>();
		ObstaclePlacer op = new ObstaclePlacer();
		
		for(int i = 0; i < 15; i ++) {
			int rand = (int) (Math.random()*3);
			switch(rand) {
			case 0 : obstacles.add(ObstacleFactory.create("rock:23", op.place())); break;
			case 1 : obstacles.add(ObstacleFactory.create("tree:22", op.place())); break;
			case 2 : obstacles.add(ObstacleFactory.create("tree:12", op.place())); break;
			}
		}
		
		obstacles.addAll(ChestsCreator.creation(chestAmount, obstacles));
		
		return obstacles;
	}

}
