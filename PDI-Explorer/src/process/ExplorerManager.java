package process;

import data.entity.Explorer;
import environmentcreation.ExplorationStrategy;

/**
 * The explorer controller class. Each instance of the class represents an
 * explorer in the simulation environment.
 *
 */
public class ExplorerManager extends Thread {
	private Simulation simulation;
	private Explorer explorer;
	private ExplorationStrategy strategy;

	private boolean dead = false;
	private boolean running = false;

	public ExplorerManager(Simulation simulation, Explorer explorer, ExplorationStrategy strategy) {
		this.simulation = simulation;
		this.explorer = explorer;
		this.strategy = strategy;
	}

	@Override
	public void run() {
		while (!dead && running) {
			SimulationUtility.unitTime();
			// TODO
		}
	}

	public String getExplorerName() {
		return explorer.getName();
	}

	public void updatePosition(double[] newPosition) {
		explorer.setPosition(newPosition);
	}

	public void updateHealth(int newHealth) {
		explorer.setHealth(newHealth);
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean isDead) {
		this.dead = isDead;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean isRunning) {
		this.running = isRunning;
	}
}