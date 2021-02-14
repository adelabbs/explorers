package environmentcreation;

import java.util.ArrayList;

import data.entity.LivingEntity;
import data.map.Map;
import data.simulation.Environment;

public class ObstaclePlacer {

	private int placed;
	private ArrayList<double[]> positions = new ArrayList<double[]>();
	
	public ObstaclePlacer() {
		placed = 0;
		configuration();
	}
	
	public double[] place() {
		double[] res = positions.get(placed);
		placed ++;
		return res;
	}
	
	private void configuration() {
		Map map = Environment.getInstance().getMap();
		int i = 0;
		double pos[] = new double[2];
		while(i < 10) {
			pos[0] = Math.random()*90;
			pos[1] = Math.random()*90;
			if(map.getTile((int) pos[0], (int) pos[1]).getType().equals("g")) {
				if(noEntities(pos)) {
					positions.add(new double[] {pos[0], pos[1]});
					i ++;
				}
			}
		}
	}
	
	private boolean noEntities(double pos[]) {
		ArrayList<LivingEntity> entities = Environment.getInstance().getEntities();
		boolean possible = true;
		int i = 0;
		int size = entities.size();
		while(i < size && possible) {
			double entityPos[] = entities.get(i).getPosition();
			possible = distanceVerif(pos, entityPos);
			i ++;
		}
		return true;
	}
	
	private static boolean distanceVerif(double[] pos, double[] entityPos) {
		double distance = Math.sqrt(Math.pow(pos[0] - entityPos[0], 2) +
				Math.pow(pos[1] - entityPos[1], 2));
		return distance >= 5;
	}
	
}
