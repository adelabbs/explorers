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

	public CollectChestAction(Chest chest, Explorer explorer) {
		this.chest = chest;
		this.explorer = explorer;
	}

	@Override
	public void execute() {
		if (SimulationUtility.distance(chest.getPosition(), explorer.getPosition()) <= explorer.getScope()) {
			collectingTime--;
			if (collectingTime == 0) {
				Environment e = Environment.getInstance();
				e.remove(chest);
				e.incrementFoundChest();
			}
		} else {
			collectingTime = -1;
		}
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
