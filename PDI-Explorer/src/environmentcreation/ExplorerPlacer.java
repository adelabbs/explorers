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
		int sent = 0;
		while(map.getTile(sent, sent).getType().equals("w"))
			sent ++;
		double[] last = new double[] {(double)(sent), (double)(sent)};
		positions.add(new double[] {last[0], last[1]});
		for(int i = 0; i < explorerAmount - 1; i ++) {
			last[1] += 8.0;
			while(map.getTile((int) last[0], (int) last[1]).getType().equals("w"))
				last[1] ++;
			positions.add(new double[] {last[0], last[1]});
//			System.out.prSintln(last[0] + " " + last[1]);
		}
//		for(double[] d : positions)
//			System.out.println(d[0] + " " + d[1]);
	}
	
}
