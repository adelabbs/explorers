package process.action;

import data.entity.Explorer;
import data.map.ExplorerMap;
import data.map.ExplorerTile;
import data.simulation.Environment;

/**
 * This class is the basic non random movement of an explorer.
 * The explorer will move toward an undiscovered destination.
 *
 */
public class ExplorationAction implements Action {

	private Explorer explorer;
	private ExplorerMoveAction ema;
	
	public ExplorationAction(Explorer explorer) {
		this.explorer = explorer;
	}
	
	@Override
	public void execute() {
		int scanningRange = 0;
		boolean newZoneDiscovered = false;
		double[] currentPosition = explorer.getPosition();
		ExplorerMap ex = explorer.getMap();
		ExplorerTile tileA;
		ExplorerTile tileB;
		ExplorerTile tileC;
		ExplorerTile tileD;
			
		while(scanningRange < 90 || newZoneDiscovered) {
	
			tileA = ex.getTile((int) currentPosition[1] + scanningRange, (int) currentPosition[0] + scanningRange);
			tileB = ex.getTile((int) currentPosition[1] - scanningRange, (int) currentPosition[0] + scanningRange);	
			tileC = ex.getTile((int) currentPosition[1] + scanningRange, (int) currentPosition[0] - scanningRange);
			tileD = ex.getTile((int) currentPosition[1] - scanningRange, (int) currentPosition[0] - scanningRange);
			
			if(!tileA.isExplored()) {
				newZoneDiscovered = true;
				createUndiscoveredMoveAction((int) currentPosition[1] + scanningRange, (int) currentPosition[0] + scanningRange);
			} else if(!tileB.isExplored()) {
				newZoneDiscovered = true;
				createUndiscoveredMoveAction((int) currentPosition[1] - scanningRange, (int) currentPosition[0] + scanningRange);
			} else if (!tileC.isExplored()) {
				newZoneDiscovered = true;
				createUndiscoveredMoveAction((int) currentPosition[1] + scanningRange, (int) currentPosition[0] - scanningRange);
			} else if (!tileD.isExplored()) {
				newZoneDiscovered = true;
				createUndiscoveredMoveAction((int) currentPosition[1] - scanningRange, (int) currentPosition[0] - scanningRange);
			} else {
				scanningRange++;
			}
		}
	}
	
	public void createUndiscoveredMoveAction(int i, int j) {
		//South
		if(explorer.getPosition()[1] < i) {
			ema = new ExplorerMoveAction(explorer, Environment.getInstance(), MoveAction.SOUTH);
			ema.execute();
		}
		//North
		else if(explorer.getPosition()[1] > i) { 
			ema = new ExplorerMoveAction(explorer, Environment.getInstance(), MoveAction.NORTH);
			ema.execute();
		}
		//East
		else if(explorer.getPosition()[0] < j) {
			ema = new ExplorerMoveAction(explorer, Environment.getInstance(), MoveAction.EAST);
			ema.execute();
		}
		//West
		else if(explorer.getPosition()[0] > j) {
			ema = new ExplorerMoveAction(explorer, Environment.getInstance(), MoveAction.WEST);
			ema.execute();
		}
	}
	
	
	@Override
	public boolean isOver() {
		return true;
	}

}
