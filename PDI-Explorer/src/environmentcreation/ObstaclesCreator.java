package environmentcreation;

import java.util.ArrayList;

import data.entity.Entity;
import environmentcreation.event.EntityCreationException;


public class ObstaclesCreator {
	
	public static ArrayList<Entity> creation() throws EntityCreationException {
		
		ArrayList<Entity> obstacles = new ArrayList<>();
		
		obstacles.add(ObstacleFactory.create("rock:23", new double[] {7.0, 18.0}));
		obstacles.add(ObstacleFactory.create("rock:23", new double[] {43.0, 52.0}));
		obstacles.add(ObstacleFactory.create("rock:23", new double[] {7.0, 81.0}));
		obstacles.add(ObstacleFactory.create("tree:22", new double[] {17.0, 18.0}));
		obstacles.add(ObstacleFactory.create("tree:22", new double[] {23.0, 14.0}));
		obstacles.add(ObstacleFactory.create("tree:22", new double[] {8.0, 80.0}));
		
		return obstacles;
	}

}
