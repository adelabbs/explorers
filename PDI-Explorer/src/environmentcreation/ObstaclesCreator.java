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
		
		obstacles.add(ObstacleFactory.create("rock:23", op.place()));
		obstacles.add(ObstacleFactory.create("rock:23", op.place()));
		obstacles.add(ObstacleFactory.create("rock:23", op.place()));
		obstacles.add(ObstacleFactory.create("tree:22", op.place()));
		obstacles.add(ObstacleFactory.create("tree:22", op.place()));
		obstacles.add(ObstacleFactory.create("tree:22", op.place()));
		obstacles.add(ObstacleFactory.create("tree:12", op.place()));
		obstacles.add(ObstacleFactory.create("tree:12", op.place()));
		obstacles.add(ObstacleFactory.create("tree:12", op.place()));
		obstacles.addAll(ChestsCreator.creation(chestAmount, obstacles));
		
		return obstacles;
	}

}
