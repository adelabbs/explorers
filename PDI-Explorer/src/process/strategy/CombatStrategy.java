package process.strategy;

import process.ExplorerManager;

public class CombatStrategy implements ExplorationStrategy {

	private ExplorerManager explorerManager;

	public CombatStrategy(ExplorerManager explorerManager) {
		this.explorerManager = explorerManager;
	}

	public ExplorerManager getExplorerManager() {
		return explorerManager;
	}

	@Override
	public void decide() {
		// Use manager data to make a decision.
	}
}