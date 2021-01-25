package environmentcreation;

import java.util.ArrayList;

import data.entity.Entity;


public class ObstaclesCreator {
	
	public static ArrayList<Entity> creation() {
		
		ArrayList<Entity> obstacles = new ArrayList<>();

		obstacles.add(new Entity("Rock", new double[] {7.0, 18.0}, new double[] {2.0, 3.0}));
		obstacles.add(new Entity("Rock", new double[] {43.0, 52.0}, new double[] {2.0, 3.0}));
		obstacles.add(new Entity("Rock", new double[] {7.0, 81.0}, new double[] {2.0, 3.0}));
		obstacles.add(new Entity("Tree", new double[] {17.0, 18.0}, new double[] {2.0, 2.0}));
		obstacles.add(new Entity("Tree", new double[] {23.0, 14.0}, new double[] {2.0, 2.0}));
		obstacles.add(new Entity("Tree", new double[] {8.0, 80.0}, new double[] {2.0, 2.0}));
		
		return obstacles;
	}

}
