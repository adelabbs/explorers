package process.action;

import data.entity.Entity;
import data.entity.Explorer;
import data.simulation.Environment;
/**
 * This action is used when two explorers are in range.
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
	 * @param me (moving explorer)
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
		
		for (int i = 0; i < SPACE_BETWEEN_EX; i++) {
			if (Math.abs(dx) >= Math.abs(dy)) {
				//X movement case
				if(dx > 0) {
					//go east
					moveExplorer(MoveAction.EAST);
				} else {
					//go west
					moveExplorer(MoveAction.WEST);
				}
			} else {
				//Y movement case
				if(dy > 0) {
					//go south
					moveExplorer(MoveAction.SOUTH);
				} else {
					//go north
					moveExplorer(MoveAction.NORTH);
				}
			}
		}
	}
	
	@Override
	public boolean isOver() {
		return true;
	}
	
	/**
	 * Same method from MoveAction
	 * TODO Use directly the method from MoveAction class
	 * @param direction
	 */
	public void moveExplorer(int direction) {		
		double currentPosY = me.getPosition()[0];
		double currentPosX = me.getPosition()[1];
		double nextPos[] = me.getPosition();
		switch (direction) {
		case MoveAction.NORTH:
			nextPos[0] -= 1;
			break;
		case MoveAction.EAST:
			nextPos[1] += 1;
			break;
		case MoveAction.WEST:
			nextPos[1] -= 1;
			break;
		case MoveAction.SOUTH:
			nextPos[0] += 1;
			break;
		}
	
		// If free :
		if (isValid(nextPos) != false) {
			// Set the position for the next tick
			me.setPosition(nextPos);
		} else {
			nextPos[0] = currentPosY;
			nextPos[1] = currentPosX;
			me.setPosition(nextPos);
		}
	}
	

	/**
	 * 
	 * @param nextPosition
	 * @return true if there is no entity and no map offset
	 */
	private boolean isValid(double[] nextPosition) {
		return checkForCollide(nextPosition) && checkRBorderMap(nextPosition) && checkLBorderMap(nextPosition);
	}

	/**
	 * 
	 * @param position
	 * @return true if there is no entity, return false otherwise
	 */
	private boolean checkForCollide(double[] position) {
		for (Entity e : Environment.getInstance().getObstacles()) {
			if ((e.getPosition()[0] == position[0]) && (e.getPosition()[1] == position[1])) {
				return false;
			}
		}

		return true;
	}
	
	private boolean checkLBorderMap(double[] position) {
		return (position[0] >= MoveAction.MAP_BORDER_MIN) && (position[1] >= MoveAction.MAP_BORDER_MIN);
	}

	private boolean checkRBorderMap(double[] position) {
		return (position[0] <= MoveAction.MAP_BORDER_MAX) && (position[1] <= MoveAction.MAP_BORDER_MAX);
	}



}
