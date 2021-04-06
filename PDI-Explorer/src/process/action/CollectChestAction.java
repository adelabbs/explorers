package process.action;

import data.entity.Chest;
import data.entity.Explorer;
import data.simulation.Environment;
import process.SimulationUtility;
import tests.manual.SimuPara;

public class CollectChestAction implements Action {
	private Chest chest;
	private Explorer explorer;
	private int collectingTime = SimuPara.CHEST_COLLECTING_TIME;
	private ExplorerMoveAction ema;

	public CollectChestAction(Chest chest, Explorer explorer) {
		this.chest = chest;
		this.explorer = explorer;
	}

	@Override
	public void execute() {
		if (SimulationUtility.distance(chest.getPosition(), explorer.getPosition()) <= explorer.getScope()) {
			if(SimulationUtility.distance(chest.getPosition(), explorer.getPosition()) <= 2) {
				collectingTime--;
				if (collectingTime == 0) {
					Environment e = Environment.getInstance();
					e.remove(chest);
				}
			} else {
				ema = new ExplorerMoveAction(explorer, Environment.getInstance(), chestDirection());
				ema.execute();
			}
			
		} else {
			collectingTime = -1;
		}
	}

	/**
	 * 
	 * @return the direction of the chest
	 */
	public int chestDirection() {
		double[] chestPos = chest.getPosition();
		double[] explorerPos = explorer.getPosition();
		int direction;
		
		double dx = (explorerPos[0] - chestPos[0]);
		double dy = (explorerPos[1] - chestPos[1]);
				
		if(Math.abs(dx) > Math.abs(dy)) {
			if(dx > 0) {
				direction = MoveAction.NORTH;
			} else {
				direction = MoveAction.SOUTH;
			}
		} else {
			if(dy > 0) {
				direction = MoveAction.WEST;
			} else {
				direction = MoveAction.EAST;
			}
		}
		return direction;
	}
	
	@Override
	public boolean isOver() {
		return collectingTime <= 0;
	}

	public Chest getChest() {
		return chest;
	}

	public int getCollectingTime() {
		return collectingTime;
	}

	public Explorer getExplorer() {
		return explorer;
	}

}
