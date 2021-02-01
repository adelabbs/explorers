package process.strategy;

import process.ExplorerManager;

public class DodgeStrategy implements ExplorationStrategy {

	private ExplorerManager explorerManager;

	public DodgeStrategy(ExplorerManager explorerManager) {
		this.explorerManager = explorerManager;
	}

	public ExplorerManager getExplorerManager() {
		return explorerManager;
	}

	@Override
	public void decide() {
		// TODO Auto-generated method stub

	}
}