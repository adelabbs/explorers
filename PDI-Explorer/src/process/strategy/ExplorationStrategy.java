package process.strategy;

import process.ExplorerManager;
import process.action.Action;

/**
 * This class allows us to abstract the decision-making process of the
 * {@link ExplorerManager}
 * 
 *
 */
public abstract class ExplorationStrategy {
	private ExplorerManager explorerManager;

	public ExplorationStrategy(ExplorerManager explorerManager) {
		this.explorerManager = explorerManager;
	}

	public ExplorerManager getExplorerManager() {
		return explorerManager;
	}

	public void setExplorerManager(ExplorerManager explorerManager) {
		this.explorerManager = explorerManager;
	}

	public void planAction(Action action) {
		explorerManager.planAction(action);
	}

	public abstract void decide();
}