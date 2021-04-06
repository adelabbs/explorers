package process.action;

import data.entity.Entity;
import data.entity.LivingEntity;
import data.simulation.Environment;

/*
 * The primary movement class for all entities in the simulation
 */
public abstract class MoveAction implements Action {
	private static final int MIN = 1;
	private static final int MAX = 4;

	public static final int NORTH = 1;
	public static final int EAST = 2;
	public static final int SOUTH = 3;
	public static final int WEST = 4;

	// Absolute border for square map
	public static final double MAP_BORDER_MAX = 89;
	public static final double MAP_BORDER_MIN = 0;

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
		try {
			double nextPos[] = getNextPosition(getEntity(), direction);

			// If free :
			if (isValid(nextPos) != false) {
				// Set the position for the next tick
				getEntity().setPosition(nextPos);
			} else {
				nextPos[0] = currentPosY;
				nextPos[1] = currentPosX;
				getEntity().setPosition(nextPos);
			}
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
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
	public boolean isValid(double[] nextPosition) {
		return checkForCollide(nextPosition) && checkRBorderMap(nextPosition) && checkLBorderMap(nextPosition);
	}

	/**
	 * 
	 * @param position
	 * @return true if there is no entity at this position
	 */
	private boolean checkForCollide(double[] position) {
		for (Entity e : Environment.getInstance().getObstacles()) {
			if ((e.getPosition()[0] == position[0]) && (e.getPosition()[1] == position[1])) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Check the left border of the map
	 * 
	 * @param position
	 * @return boolean
	 */
	private boolean checkLBorderMap(double[] position) {
		return (position[0] >= MAP_BORDER_MIN) && (position[1] >= MAP_BORDER_MIN);
	}

	/**
	 * Check the right border of the map
	 * 
	 * @param position
	 * @return boolean
	 */
	private boolean checkRBorderMap(double[] position) {
		return (position[0] <= MAP_BORDER_MAX) && (position[1] <= MAP_BORDER_MAX);
	}

	/**
	 * 
	 * @return a random direction (NORTH, SOUTH, EAST ,WEST)
	 */
	public static int pickRandomDirection() {
		return (int) (MIN + Math.random() * ((MAX - MIN) + 1));
	}

	public double[] getNextPosition(LivingEntity entity, int direction) throws IllegalArgumentException {
		double currentPosY = entity.getPosition()[0];
		double currentPosX = entity.getPosition()[1];
		double multiplier;
		double nextPos[] = entity.getPosition();

		if (Environment.getInstance().getMap().getTile((int) currentPosY, (int) currentPosX).getType() == "w")
			multiplier = 0.5;
		else
			multiplier = 1;

		switch (direction) {
		case NORTH:
			nextPos[0] -= 0.025 * getEntity().getSpeed() * multiplier;
			break;
		case EAST:
			nextPos[1] += 0.025 * getEntity().getSpeed() * multiplier;
			break;
		case WEST:
			nextPos[1] -= 0.025 * getEntity().getSpeed() * multiplier;
			break;
		case SOUTH:
			nextPos[0] += 0.025 * getEntity().getSpeed() * multiplier;
			break;
		default:
			throw new IllegalArgumentException("Unknown direction" + direction);
		}
		return nextPos;

	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public abstract LivingEntity getEntity();

	public Environment getEnvironment() {
		return environment;
	}

}