package environmentcreation;

import data.entity.Obstacle;
import environmentcreation.event.EntityCreationException;

public class ObstacleFactory {

	public static Obstacle create(String type, double[] pos) throws EntityCreationException {

		switch (type) {

		case "rock:23":
			return new Obstacle(type, pos, new double[] { 2.0, 3.0 });
		case "tree:12":
			return new Obstacle(type, pos, new double[] { 1.0, 2.0 });
		case "tree:22":
			return new Obstacle(type, pos, new double[] { 2.0, 2.0 });
		default:
			throw new EntityCreationException("Couldn't create " + type);

		}

	}

}
