package process;

import java.util.ArrayList;

import data.entity.Explorer;
import process.action.Action;
import process.strategy.ExplorationStrategy;

/**
 * The explorer controller class. Each instance of the class represents an
 * explorer in the simulation environment.
 *
 */
public class ExplorerManager extends Thread implements LivingEntityManager {
	private Simulation simulation;
	private Explorer explorer;
	private ExplorationStrategy strategy;
	private ArrayList<Action> actions = new ArrayList<Action>();

	private boolean dead = false;
	private boolean running = false;

	public ExplorerManager(Simulation simulation, Explorer explorer) {
		this.simulation = simulation;
		this.explorer = explorer;
	}

	@Override
	public void run() {
		while (!dead && running) {
			SimulationUtility.unitTime();
			strategy.decide();
			for (Action action : actions) {
				action.execute();
			}
			actions.clear();
		}
	}

	public void planAction(Action action) {
		if (!actions.contains(action)) {
			actions.add(action);
		}
	}

	public void remove(Action action) {
		actions.remove(action);
	}

	public Simulation getSimulation() {
		return simulation;
	}

	public Explorer getExplorer() {
		return explorer;
	}

	public String getExplorerName() {
		return explorer.getName();
	}

	public ExplorationStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(ExplorationStrategy strategy) {
		this.strategy = strategy;
	}

	@Override
	public void updatePosition(double[] newPosition) {
		explorer.setPosition(newPosition);
	}

	@Override
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