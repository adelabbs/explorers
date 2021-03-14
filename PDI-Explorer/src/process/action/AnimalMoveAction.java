package process.action;

import data.entity.Animal;
import data.entity.Entity;
import data.entity.Explorer;
import data.simulation.Environment;
import process.SimulationUtility;

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
		double closestHumanPos[];
		if((closestHumanPos = closestHumanInScope()) != null) {
			newPos = new double[] {getEntity().getPosition()[0], getEntity().getPosition()[1]};
			double vx = closestHumanPos[0] - entity.getPosition()[0];
			double vy = closestHumanPos[1] - entity.getPosition()[1];
			if(Math.abs(vx) > Math.abs(vy)) {
				if(vx < 0) {
					newPos[0] --;
				} else {
					newPos[0] ++;
				}
			} else {
				if(vy < 0) {
					newPos[1] --;
				} else {
					newPos[1] ++;
				}
			}
			//If contact with a human
			Explorer explorer = getClosestExplorer();
			if(explorer != null) {
				fight(explorer, entity);
			}
			
		}
		else {
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
		}
		getEntity().setPosition(newPos);
	}
		
	
	//Damage
	private void fight(Explorer explorer, Animal animal) {
		int explorersLife = explorer.getHealth();
		int animalsLife = animal.getHealth();
		
		explorer.setHealth(explorersLife - animal.getDamage());
		animal.setHealth(animalsLife - explorer.getDamage());
	}
	
	
	private double distanceFromInitialPos(double newPos[]) {
		return Math.sqrt(Math.pow(newPos[0] - getEntity().getInitPosition()[0], 2)
				+ Math.pow(newPos[1] - getEntity().getInitPosition()[1], 2));
	}
	
	private boolean outOfBorder(double[] newPos) {
		int i = (int) newPos[0];
		int j = (int) newPos[1];
		return (i >= 0 && i <= (89 - (int) getEntity().getSize()[0] - 1) && j >= 0 && j <= (89 - (int) getEntity().getSize()[1] - 1));
	}

	public Animal getEntity() {
		return entity;
	}
	
	private Explorer getClosestExplorer() {
		for(Entity e : Environment.getInstance().getEntities()) {
			if(e.getType().equals("Explorer") && 
					SimulationUtility.distance(entity.getPosition(), e.getPosition()) <= 1) {
				return (Explorer) e;
			}
		}
		return null;
	}
	
	
	private double[] closestHumanInScope() {
		double[] closestPosition = null;
		double maxDistance = 1000;
		for(Entity e : Environment.getInstance().getEntities()) {
			if(e.getType().equals("Explorer")) {
				double distance = SimulationUtility.distance(entity.getPosition(), e.getPosition());
				if(distance < maxDistance
						&& distanceFromInitialPos(e.getPosition()) < ANIMAL_MAX_TERRITORY) {
					if(distance < entity.getScope()) {
						maxDistance = distance;
						closestPosition = new double[] {e.getPosition()[0], e.getPosition()[1]};
					} else {
						maxDistance = imprecise(distance);
						closestPosition = imprecise(e.getPosition());
					}					
				}
			}
		}
		return closestPosition;
	}
	
	private double imprecise(double value) {
		double gap = Math.random() * 2* value/10;
		gap -= value/10;
		return value + gap;
	}
	
	private double[] imprecise(double[] values) {
		double[] copiedValues = new double[] {values[0], values[1]};
		for(int i = 0; i < values.length; i ++) {
			copiedValues[i] = imprecise(copiedValues[i]);
		}
		return copiedValues;
	}

}
