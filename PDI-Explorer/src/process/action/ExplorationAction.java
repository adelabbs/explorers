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
//	public void execute() {
//		int scanningRange = 0;
//		boolean newZoneDiscovered = false;
//		double[] currentPosition = explorer.getPosition();
//		ExplorerMap ex = explorer.getMap();
//		ExplorerTile tileA;
//		ExplorerTile tileB;
//		ExplorerTile tileC;
//		ExplorerTile tileD;
//			
//		while(scanningRange < 90 || newZoneDiscovered) {
//	
//			tileA = ex.getTile((int) currentPosition[1] + scanningRange, (int) currentPosition[0] + scanningRange);
//			tileB = ex.getTile((int) currentPosition[1] - scanningRange, (int) currentPosition[0] + scanningRange);	
//			tileC = ex.getTile((int) currentPosition[1] + scanningRange, (int) currentPosition[0] - scanningRange);
//			tileD = ex.getTile((int) currentPosition[1] - scanningRange, (int) currentPosition[0] - scanningRange);
//			
//			if(!tileA.isExplored()) {
//				newZoneDiscovered = true;
//				createUndiscoveredMoveAction((int) currentPosition[1] + scanningRange, (int) currentPosition[0] + scanningRange);
//			} else if(!tileB.isExplored()) {
//				newZoneDiscovered = true;
//				createUndiscoveredMoveAction((int) currentPosition[1] - scanningRange, (int) currentPosition[0] + scanningRange);
//			} else if (!tileC.isExplored()) {
//				newZoneDiscovered = true;
//				createUndiscoveredMoveAction((int) currentPosition[1] + scanningRange, (int) currentPosition[0] - scanningRange);
//			} else if (!tileD.isExplored()) {
//				newZoneDiscovered = true;
//				createUndiscoveredMoveAction((int) currentPosition[1] - scanningRange, (int) currentPosition[0] - scanningRange);
//			} else {
//				scanningRange++;
//			}
//		}
//	}
	
	public void execute() {
		int range = explorer.getScope() + 1;
		ExplorerMap explorerMap = explorer.getMap();
		int x = (int) explorer.getPosition()[0];
		int y = (int) explorer.getPosition()[1];
		boolean move = false;
		int distMax = 1500;
		int tempI = 0;
		int tempJ = 0;
		while(!move) {
			for(int i = x - range/2; i < x + range/2; i ++) {
				for(int j = y - range/2; j < y + range/2; j ++) {
					if(!oob(i, j))
						if(!explorerMap.getTile(i, j).isExplored()) {
							int tempX = x - i;
							int tempY = y - j;
							int distance = distance(tempX, tempY);
							if(distance <= distMax) {
								int rand = (int) Math.random()*2;
								if(distance < distMax || rand == 1) {
									tempI = i;
									tempJ = j;
								}
							}
							move = true;
						}
				}
			}
			range ++;
		}
		createMoveAction(tempI, tempJ);
	}
	
	private void createMoveAction(int i, int j) {
		int vx = i - (int) explorer.getPosition()[0];
		int vy = j - (int) explorer.getPosition()[1];
		if(Math.abs(vx) > Math.abs(vy)) {
			if(vx < 0) {
				ema = new ExplorerMoveAction(explorer, Environment.getInstance(), MoveAction.NORTH);
				ema.execute();
			} else {
				ema = new ExplorerMoveAction(explorer, Environment.getInstance(), MoveAction.SOUTH);
				ema.execute();
			}
		} else {
			if(vy < 0) {
				ema = new ExplorerMoveAction(explorer, Environment.getInstance(), MoveAction.WEST);
				ema.execute();
			} else {
				ema = new ExplorerMoveAction(explorer, Environment.getInstance(), MoveAction.EAST);
				ema.execute();
			}
		}
	}
	
	private boolean oob(int i, int j) {
		return i >= 90 || j >= 90 || i < 0 || j < 0;
	}
	
	private int distance(int i, int j) {
		return (int) (Math.sqrt(Math.pow((double) i - explorer.getPosition()[0], 2) + Math.pow((double) i - explorer.getPosition()[0], 2)));
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
