package process.strategy;

import process.ExplorerManager;
import process.action.MoveAction;

public class RandomStrategy extends ExplorationStrategy {

	public RandomStrategy(ExplorerManager explorerManager) {
		super(explorerManager);
	}

	@Override
	public void decide() {
		MoveAction action = new MoveAction(getExplorerManager().getExplorer());
		super.planAction(action);
	}

}