package environmentcreation;

import java.util.ArrayList;

import data.map.Map;
import data.simulation.Environment;

public class ExplorerPlacer {

	private int placed;
	private ArrayList<double[]> positions = new ArrayList<double[]>();
	
	public ExplorerPlacer(int explorerAmount) {
		placed = 0;
		configuration(explorerAmount);
	}
	
	public double[] place() {
		double[] res = positions.get(placed);
		placed ++;
		return res;
	}
	
	private void configuration(int explorerAmount) {
		Map map = Environment.getInstance().getMap();
		
		//places explorers on top of the map
		int sent = 0;
		while(map.getTile(sent, sent).getType().equals("w"))
			sent ++;
		double[] last = new double[] {(double)(sent), (double)(sent)};
		positions.add(new double[] {last[0], last[1]});
		int i = 0;
		while(i < explorerAmount - 1 && !oob(last)) {
			last[1] += 15.0;
			boolean placeable = false;
			while(!oob(last) && !placeable)
				if(map.getTile((int) last[0], (int) last[1]).getType().equals("w"))
					last[1] ++;
				else placeable = true;
			if(placeable) {
				positions.add(new double[] {last[0], last[1]});
				i ++;
			}
		}
		
		//places explorers on bottom of the map
		sent = 89;
		while(map.getTile(sent, sent).getType().equals("w"))
			sent --;
		last = new double[] {(double)(sent), (double)(sent)};
		positions.add(new double[] {last[0], last[1]});
		while(i < explorerAmount - 1 && !oob(last)) {
			last[1] -= 15.0;
			while(map.getTile((int) last[0], (int) last[1]).getType().equals("w"))
				last[1] --;
			positions.add(new double[] {last[0], last[1]});
			i ++;
		}
	}
	
	private boolean oob(double[] pos) {
		return pos[0] < 0 || pos[0] >= 90 || pos[1] < 0 || pos[1] >= 90;
	}
	
}
