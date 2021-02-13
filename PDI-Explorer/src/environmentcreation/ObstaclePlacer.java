package environmentcreation;

import java.util.ArrayList;

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
				positions.add(new double[] {pos[0], pos[1]});
				i ++;
			}
				
		}
	}
	
	
}
