package process.action;

import data.entity.LivingEntity;
import data.simulation.Environment;

public class MoveAction implements Action {
	private static final int MIN = 1;
	private static final int MAX = 4;

	public static final int NORTH = 1;
	public static final int EAST = 2;
	public static final int SOUTH = 3;
	public static final int WEST = 4;

	int direction;
	LivingEntity entity;
	Environment environment;

	public MoveAction(LivingEntity entity, Environment environment) {
		direction = pickRandomDirection();
		this.entity = entity;
	}

	public MoveAction(LivingEntity entity, Environment environment, int direction) {
		this.direction = direction;
	}

	@Override
	public void execute() {
		entity.setPosition(null);
	}

	private int pickRandomDirection() {
		return (int) (MIN + Math.random() * ((MAX - MIN) + 1));
	}
}