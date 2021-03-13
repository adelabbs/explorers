package process.action;

import data.entity.Animal;
import data.entity.Explorer;
import data.simulation.Environment;

public class RunAwayAction implements Action {
	
	private Explorer explorer;
	private Animal animal;
	
	
	public RunAwayAction (Explorer explorer, Animal animal) {
		this.explorer = explorer;
		this.animal = animal;
	}
	
	@Override
	public void execute() {
		double[] explorerPos = explorer.getPosition();
		double[] animalPos = animal.getPosition();
		double dx = explorerPos[0] - animalPos[0];
		double dy = explorerPos[1] - animalPos[1];
		int direction;

		if (Math.abs(dx) >= Math.abs(dy)) {
			// X movement case
			if (dx > 0) {
				// go east
				direction = MoveAction.EAST;
			} else {
				// go west
				direction = MoveAction.WEST;
			}
		} else {
			// Y movement case
			if (dy > 0) {
				// go south
				direction = MoveAction.SOUTH;
			} else {
				// go north
				direction = MoveAction.NORTH;
			}
		}
		ExplorerMoveAction move = new ExplorerMoveAction(explorer, Environment.getInstance(), direction);
		move.execute();

	}

	@Override
	public boolean isOver() {
		return true;
	}

}
