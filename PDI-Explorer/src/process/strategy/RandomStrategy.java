package process.strategy;

import data.simulation.Environment;
import process.action.ExplorerMoveAction;
import process.action.MoveAction;
import process.manager.ExplorerManager;

/**
 * 
 * Random movement strategy
 *
 */
public class RandomStrategy extends ExplorationStrategy {

	public RandomStrategy(ExplorerManager explorerManager) {
		super(explorerManager);
	}

	@Override
	public void decide() {
		MoveAction action = new ExplorerMoveAction(getExplorerManager().getExplorer(),
				Environment.getInstance());
		super.planAction(action);
	}

}