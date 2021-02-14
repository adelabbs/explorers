package process.action;

import data.entity.Entity;
import data.entity.LivingEntity;
import data.simulation.Environment;

public abstract class MoveAction implements Action {
	private static final int MIN = 1;
	private static final int MAX = 4;

	public static final int NORTH = 1;
	public static final int EAST = 2;
	public static final int SOUTH = 3;
	public static final int WEST = 4;

	// Absolute border for square map
	private static double MAP_BORDER_MAX = 89;
	private static double MAP_BORDER_MIN = 0;

	private int direction;
	private Environment environment;

	public MoveAction(Environment environment) {
		direction = pickRandomDirection();
		this.environment = environment;
	}

	public MoveAction(Environment environment, int direction) {
		this.environment = environment;
		this.direction = direction;
	}

	@Override
	public void execute() {
		double currentPosY = getEntity().getPosition()[0];
		double currentPosX = getEntity().getPosition()[1];
		double nextPos[] = getEntity().getPosition();
		switch (direction) {
		case NORTH:
			nextPos[0] -= 1;
			break;
		case EAST:
			nextPos[1] += 1;
			break;
		case WEST:
			nextPos[1] -= 1;
			break;
		case SOUTH:
			nextPos[0] += 1;
			break;
		}

		// If free :
		if (isValid(nextPos) != false) {
			// Set the position for the next tick
			getEntity().setPosition(nextPos);
		} else {
			nextPos[0] = currentPosY;
			nextPos[1] = currentPosX;
			getEntity().setPosition(nextPos);
		}
	}

	@Override
	public boolean isOver() {
		return true;
	}

	/**
	 * This method is used to know if the entity's destination is valid
	 * 
	 * @param nextPosition
	 * @return true if the destination is free
	 */
	private boolean isValid(double[] nextPosition) {
		return checkForCollide(nextPosition) && checkRBorderMap(nextPosition) && checkLBorderMap(nextPosition);
	}

	private boolean checkForCollide(double[] position) {
		for (Entity e : Environment.getInstance().getObstacles()) {
			if ((e.getPosition()[0] == position[0]) && (e.getPosition()[1] == position[1])) {
				return false;
			}
		}

		return true;
	}

	private boolean checkLBorderMap(double[] position) {
		return (position[0] >= MAP_BORDER_MIN) && (position[1] >= MAP_BORDER_MIN);
	}

	private boolean checkRBorderMap(double[] position) {
		return (position[0] <= MAP_BORDER_MAX) && (position[1] <= MAP_BORDER_MAX);
	}

	private int pickRandomDirection() {
		return (int) (MIN + Math.random() * ((MAX - MIN) + 1));
	}

	public int getDirection() {
		return direction;
	}

	public abstract LivingEntity getEntity();

	public Environment getEnvironment() {
		return environment;
	}

}