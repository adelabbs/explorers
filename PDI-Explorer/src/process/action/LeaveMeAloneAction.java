package process.action;

import data.entity.Explorer;
import data.simulation.Environment;

/**
 * This action is used when two explorers are in range.
 * 
 * @author Geoffroy
 *
 */
public class LeaveMeAloneAction implements Action {

	public static final int SPACE_BETWEEN_EX = 10;

	private Explorer detected;
	private Explorer me;

	/**
	 * 
	 * @param detected (detected explorer)
	 * @param me       (moving explorer)
	 */
	public LeaveMeAloneAction(Explorer me, Explorer detected) {
		this.detected = detected;
		this.me = me;
	}

	@Override
	public void execute() {
		double[] myPos = me.getPosition();
		double[] hisPos = detected.getPosition();
		double dx = myPos[0] - hisPos[0];
		double dy = myPos[1] - hisPos[1];
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
		ExplorerMoveAction move = new ExplorerMoveAction(me, Environment.getInstance(), direction);
		move.execute();

	}

	@Override
	public boolean isOver() {
		return true;
	}

}
