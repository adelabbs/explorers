package process.action;

import data.entity.Explorer;

/**
 * This class is the basic non random movement of an explorer.
 * The explorer will move toward an undiscovered destination.
 * 
 * @author Geoffroy
 *
 */
public class ExplorationAction implements Action {

	private Explorer explorer;
	
	
	public ExplorationAction(Explorer explorer) {
		this.explorer = explorer;
	}
	
	@Override
	public void execute() {
		//TODO 
	}
	
	
	@Override
	public boolean isOver() {
		return true;
	}

}
