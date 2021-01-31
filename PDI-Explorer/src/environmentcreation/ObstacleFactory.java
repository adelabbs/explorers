package environmentcreation;

import data.entity.Entity;
import environmentcreation.event.EntityCreationException;

public class ObstacleFactory {

	public static Entity create(String type, double[] pos) throws EntityCreationException {
		
		switch(type) {
		
		case "rock:23" :	return new Entity(type, pos, new double[] {2.0, 3.0});
		case "tree:12" :	return new Entity(type, pos, new double[] {1.0, 2.0});
		case "tree:22" :	return new Entity(type, pos, new double[] {2.0, 2.0});
		default :			throw new EntityCreationException("Couldn't create " + type);
		
		}
		
	}
	
}
