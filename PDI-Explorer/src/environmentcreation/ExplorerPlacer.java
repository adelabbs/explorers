package environmentcreation;

import java.util.ArrayList;

import data.map.Map;
import data.simulation.Environment;

/**
 * Places the explorer on the map, all on a different region.
 * 
 * @author Léo
 *
 */
public class ExplorerPlacer {

	private int placed;
	private ArrayList<double[]> positions = new ArrayList<double[]>();
	
	/**
	 * Constructor.
	 * 
	 * @param explorerAmount The amount of explorers to place.
	 */
	public ExplorerPlacer(int explorerAmount) {
		placed = 0;
		configuration(explorerAmount);
	}
	
	/**
	 * Gives on explorer's position, will never give twice the same.
	 * 
	 * @return A position.
	 */
	public double[] place() {
		double[] res = positions.get(placed);
		placed ++;
		return res;
	}
	
	/**
	 * Generates the positions ArrayList, there can not be twice the same position.
	 * Every explorer is placed in a region.
	 * 
	 * @param explorerAmount The amount of explorer to place.
	 */
	private void configuration(int explorerAmount) {
		Map map = Environment.getInstance().getMap();
		
		int temp = 0;
		for(int i = 0; i <= explorerAmount; i ++) {
			if(i < 6) {
				int x = 0;
				while(x < 15 && temp < i) {
					int y = 0;
					while(y < 15 && temp < i) {
						if(map.getTile(x, y+(i*15)).getType().equals("g")){
							positions.add(new double[] {(double) x, (double) y+(i*15)});
							temp ++;
						}
						y ++;
					}
					x ++;
				}
			}
		}
		
		if(temp < explorerAmount) {
			int val = explorerAmount - temp;
			for(int i = 0; i < val; i ++) {
				if(i < 6) {
					int x = 0;
					while(x < 15 && temp <= explorerAmount) {
						int y = 0;
						while(y < 15 && temp <= explorerAmount) {
							if(map.getTile(x+75, y+(i*15)).getType().equals("g")){
								positions.add(new double[] {(double) x+75, (double) y+(i*15)});
								temp ++;
							}
							y ++;
						}
						x ++;
					}
				}
			}
		}
		
	}
	
}
