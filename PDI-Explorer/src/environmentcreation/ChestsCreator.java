package environmentcreation;

import java.util.ArrayList;

import data.entity.Chest;
import data.entity.Entity;
import data.simulation.Environment;

public class ChestsCreator {

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
		//debug
		chests.add(new Chest(new double[] {15, 15}));
		return chests;
	}
	
	//verifies if there is an obstacle in less than 5 tiles around
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

	//Verifies the distance between a chest and an obstacle.
	private static boolean distanceVerif(double[] pos, double[] obstaclePos) {
		double distance = Math.sqrt(Math.pow(pos[0] - obstaclePos[0], 2) +
				Math.pow(pos[1] - obstaclePos[1], 2));
		return distance >= 5;
	}
	
}
