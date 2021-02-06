package process.strategy;

import process.manager.ExplorerManager;

/**
 * With the combat strategy the explorers will regroup to fight any animal they
 * encounter
 *
 */
public class CombatStrategy extends ExplorationStrategy {

	public CombatStrategy(ExplorerManager explorerManager) {
		super(explorerManager);
	}

	@Override
	public void decide() {
		// TODO
	}

}