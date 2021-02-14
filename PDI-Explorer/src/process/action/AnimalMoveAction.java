package process.action;

import data.entity.Animal;
import data.simulation.Environment;

public class AnimalMoveAction extends MoveAction {

	// Territory's borders for all animals
	private static final double ANIMAL_MAX_TERRITORY = 10;

	private Animal entity;

	public AnimalMoveAction(Animal entity, Environment environment) {
		super(environment);
		this.entity = entity;
	}

	public AnimalMoveAction(Animal entity, Environment environment, int direction) {
		super(environment, direction);
		this.entity = entity;
	}

	@Override
	public void execute() {
		double newPos[];
		do {
			newPos = new double[] {getEntity().getPosition()[0], getEntity().getPosition()[1]};
			int direction = (int) (Math.random() * 4);
			if (direction == 4)
				direction = 3;
			switch (direction) {
			case 0:
				newPos[0]++;
				break;
			case 1:
				newPos[0]--;
				break;
			case 2:
				newPos[1]++;
				break;
			case 3:
				newPos[1]--;
				break;
			}
		} while (distanceFromInitialPos(newPos) > ANIMAL_MAX_TERRITORY && !outOfBorder(newPos));
		getEntity().setPosition(newPos);
	}

	private double distanceFromInitialPos(double newPos[]) {
		return Math.sqrt(Math.pow(newPos[0] - getEntity().getInitPosition()[0], 2)
				+ Math.pow(newPos[1] - getEntity().getInitPosition()[1], 2));
	}
	
	private boolean outOfBorder(double[] newPos) {
		int i = (int) newPos[0];
		int j = (int) newPos[1];
		return (i >= 0 && i < (90 - (int) getEntity().getSize()[0] - 1) && j >= 0 && j < (90 - (int) getEntity().getSize()[1] - 1));
	}

	public Animal getEntity() {
		return entity;
	}
}
