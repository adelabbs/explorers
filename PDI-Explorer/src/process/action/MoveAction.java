package process.action;

import java.util.ArrayList;

import data.entity.Entity;
import data.entity.LivingEntity;
import data.simulation.Environment;

public class MoveAction implements Action {
	private static final int MIN = 1;
	private static final int MAX = 4;

	public static final int NORTH = 1;
	public static final int EAST = 2;
	public static final int SOUTH = 3;
	public static final int WEST = 4;

	private int direction;
	private LivingEntity entity;
	private Environment environment;
	private ArrayList<Entity> obstacles = null;

	public MoveAction(LivingEntity entity, Environment environment) {
		direction = pickRandomDirection();
		this.entity = entity;
		this.environment = environment;
		obstacles = environment.getObstacles();
	}

	public MoveAction(LivingEntity entity, Environment environment, int direction) {
		this.entity = entity;
		this.environment = environment;
		this.direction = direction;
		obstacles = environment.getObstacles();
	}

	@Override
	public void execute() {
		double nextPos[] = entity.getPosition();
		switch (direction) {
		case NORTH:
			nextPos[1] += 1;
			break;
		case EAST:
			nextPos[0] += 1;
			break;
		case WEST:
			nextPos[0] -= 1;
			break;
		case SOUTH:
			nextPos[1] -= 1;
			break;
		}

		// If free :
		if (checkForCollide(nextPos)) {
			// Set the position for the next tick
			entity.setPosition(nextPos);
		}
	}

	/**
	 * This method is used to know if the entity's destination is free
	 * 
	 * @param nextPosition
	 * @return true if the destination is free
	 */
	private boolean checkForCollide(double[] nextPosition) {
		for (Entity e : obstacles) {
			if (e.getPosition() == nextPosition) {
				return false;
			}
		}
		return true;
	}

	private int pickRandomDirection() {
		return (int) (MIN + Math.random() * ((MAX - MIN) + 1));
	}

	public int getDirection() {
		return direction;
	}

	public LivingEntity getEntity() {
		return entity;
	}

	public Environment getEnvironment() {
		return environment;
	}

}