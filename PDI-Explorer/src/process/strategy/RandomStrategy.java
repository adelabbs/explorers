package process.strategy;

import data.simulation.Environment;
import process.action.MoveAction;
import process.manager.ExplorerManager;

public class RandomStrategy extends ExplorationStrategy {

	public RandomStrategy(ExplorerManager explorerManager) {
		super(explorerManager);
	}

	@Override
	public void decide() {
		MoveAction action = new MoveAction(getExplorerManager().getExplorer(),
				Environment.getInstance());
		super.planAction(action);
	}

}