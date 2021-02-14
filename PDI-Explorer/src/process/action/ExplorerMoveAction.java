package process.action;

import data.entity.Explorer;
import data.simulation.Environment;

public class ExplorerMoveAction extends MoveAction {

	public ExplorerMoveAction(Explorer entity, Environment environment) {
		super(entity, environment);
	}

	public ExplorerMoveAction(Explorer entity, Environment environment, int direction) {
		super(entity, environment, direction);
	}

	@Override
	public Explorer getEntity() {
		return (Explorer) entity;
	}

}
