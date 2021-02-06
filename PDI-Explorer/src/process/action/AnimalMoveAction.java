package process.action;

import data.entity.LivingEntity;
import data.simulation.Environment;

public class AnimalMoveAction extends MoveAction {

	// Territory's borders for all animals
	private static final double ANIMAL_MAX_TERRITORY = 20;

	public AnimalMoveAction(LivingEntity entity, Environment environment) {
		super(entity, environment);
	}

	public AnimalMoveAction(LivingEntity entity, Environment environment, int direction) {
		super(entity, environment, direction);
	}

	@Override
	public void execute() {
		double[] nextPosition = getEntity().getPosition();
		// If the entity is located on his territory's border :
		if (Math.abs(nextPosition[0]) >= ANIMAL_MAX_TERRITORY || Math.abs(nextPosition[1]) >= ANIMAL_MAX_TERRITORY) {
			// axe X
			if (nextPosition[0] > 0) {
				nextPosition[0]--;
			} else {
				nextPosition[0]++;
			}
			// axe Y
			if (nextPosition[1] > 0) {
				nextPosition[1]--;
			} else {
				nextPosition[1]++;
			}
			// Set new location
			getEntity().setPosition(nextPosition);
		}
		// If not, random movement :
		else {
			super.execute();
		}
	}

}
