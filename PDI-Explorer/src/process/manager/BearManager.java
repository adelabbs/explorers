package process.manager;

import data.entity.Bear;
import process.Simulation;

public class BearManager extends LivingEntityManager {
	private Simulation simulation;
	private Bear bear;

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
		// TODO Auto-generated method stub
	}

	@Override
	public void updatePosition(double[] newPosition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateHealth(int newHealth) {
		// TODO Auto-generated method stub

	}

}
