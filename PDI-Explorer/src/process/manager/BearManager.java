package process.manager;

import data.entity.Bear;
import data.simulation.Environment;
import process.Simulation;
import process.SimulationUtility;
import process.action.Action;
import process.action.AnimalMoveAction;

public class BearManager extends LivingEntityManager {
	private Simulation simulation;
	private Bear bear;
	private Action action = null;

	public BearManager(Simulation simulation, Bear bear) {
		this.simulation = simulation;
		this.bear = bear;
	}

	public Simulation getSimulation() {
		return simulation;
	}

	public void setSimulation(Simulation simulation) {
		this.simulation = simulation;
	}

	public Bear getBear() {
		return bear;
	}

	public void setBear(Bear bear) {
		this.bear = bear;
	}

	@Override
	public void run() {
		while (!isDead() && isRunning()) {
			SimulationUtility.unitTime();
			action = new AnimalMoveAction(bear, Environment.getInstance());
			action.execute();
		}
	}

	@Override
	public void updatePosition(double[] newPosition) {
		bear.setPosition(newPosition);
	}

	@Override
	public void updateHealth(int newHealth) {
		bear.setHealth(newHealth);
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

}
