package process.action;

import data.entity.Explorer;
import data.simulation.Environment;

/**
 * 
 * The basic movement for an explorer.
 *
 */
public class ExplorerMoveAction extends MoveAction {

	private Explorer entity;

	public ExplorerMoveAction(Explorer entity, Environment environment) {
		super(environment);
		this.entity = entity;
	}

	public ExplorerMoveAction(Explorer entity, Environment environment, int direction) {
		super(environment, direction);
		this.entity = entity;
	}

	@Override
	public Explorer getEntity() {
		return entity;
	}

	@Override
	public void execute() {
		super.execute();
		UpdateExplorerMap.update(entity);
	}

}
