package environmentcreation;

import java.util.ArrayList;

import data.entity.Chest;
import data.entity.Entity;
import data.simulation.Environment;

/**
 * Creates the chest and gives them a position.
 * 
 * @author Léo
 *
 */
public class ChestsCreator {

	/**
	 * Places the chests
	 * 
	 * @param chestAmount The amount of chest to place
	 * @param obstacles The obstacles already on map, in order not to put the chest at the same place
	 * @return The chest ArrayList.
	 */
	public static ArrayList<Chest> creation(int chestAmount, ArrayList<Entity> obstacles){
		ArrayList<Chest> chests = new ArrayList<>();
		double x = 0, y = 0;
		int i = 0;
		while(i < chestAmount) {
			x = Math.random()*90;
			y = Math.random()*90;
			if(Environment.getInstance().getMap().getTile((int) x, (int) y).getType().equals("g")
					&& obstacleVerification(new double[] {x, y}, obstacles)) {
				chests.add(new Chest(new double[] {x, y}));
				i ++;
			}
		}
		return chests;
	}
	
	/**
	 * Verifies if there is an obstacle in less than 5 tiles around.
	 * 
	 * @param pos The position to verify.
	 * @param obstacles The obstacles to verify collision with.
	 * @return True if there is no obstacle close, false if not.
	 */
	private static boolean obstacleVerification(double[] pos, ArrayList<Entity> obstacles) {
		boolean possible = true;
		int i = 0;
		int arraySize = obstacles.size();
		while(i < arraySize && possible) {
			double obstaclePos[] = obstacles.get(i).getPosition();
			possible = distanceVerif(pos, obstaclePos);
			if(possible)
				i ++;
		}
		return possible;
	}

	/**
	 * Verifies the distance between a chest and an obstacle.
	 * 
	 * @param pos The position of the chest.
	 * @param obstaclePos The position we verify distance with.
	 * @return True if there is more than five of distance, false if not.
	 */
	private static boolean distanceVerif(double[] pos, double[] obstaclePos) {
		double distance = Math.sqrt(Math.pow(pos[0] - obstaclePos[0], 2) +
				Math.pow(pos[1] - obstaclePos[1], 2));
		return distance >= 5;
	}
	
}
