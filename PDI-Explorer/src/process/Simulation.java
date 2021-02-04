package process;

import java.util.ArrayList;

import data.simulation.SimulationEntry;
import environmentcreation.EnvironmentCreator;
import environmentcreation.event.EntityCreationException;
import process.factory.ManagerFactory;
import data.entity.Explorer;
import data.simulation.Environment;

/**
 * The Simulation processing class. It contains and prepares all
 * {@link ExplorerManager}.
 * 
 * @author dedely
 *
 */
public class Simulation {
	private SimulationEntry simulationEntry;
	private Environment environment;
	private SimulationState state;

	private ArrayList<ExplorerManager> explorerManagers = new ArrayList<ExplorerManager>();

	/**
	 * 
	 * @param simulationEntry the simulation entry parameters
	 */
	public Simulation(SimulationEntry simulationEntry) {
		this.simulationEntry = simulationEntry;
		buildSimulation();
	}

	private void buildSimulation() {
		int explorerAmount = simulationEntry.getExplorerAmount();
		int animalAmount = simulationEntry.getAnimalAmount();
		int chestAmount = simulationEntry.getChestAmount();
		int strategy = simulationEntry.getExplorationStrategy();
		try {
			environment = EnvironmentCreator.creation(explorerAmount, animalAmount, chestAmount);
			for (Explorer explorer : environment.getExplorers()) {
				ExplorerManager explorerManager = ManagerFactory.createExplorerManager(this, explorer, strategy);
				explorerManagers.add(explorerManager);
			}
			setState(SimulationState.READY);
		} catch (EntityCreationException e) {
			e.printStackTrace();
		}
	}

	public void launch() {
		startAllExplorerManagers();
		setState(SimulationState.RUNNING);
	}

	public void update() {
		if (environment.getExplorerAmount() <= 0 || (environment.getFoundChest() >= environment.getChestAmount())) {
			setState(SimulationState.OVER);
			stopAllExplorerManagers();
		}
	}

	public void startAllExplorerManagers() {
		for (ExplorerManager explorerManager : explorerManagers) {
			explorerManager.setRunning(true);
			explorerManager.start();
		}
	}

	public void stopAllExplorerManagers() {
		for (ExplorerManager explorerManager : explorerManagers) {
			explorerManager.setRunning(false);
		}
	}

	public void add(ExplorerManager explorerManager) {
		explorerManagers.add(explorerManager);
	}

	public ArrayList<ExplorerManager> getExplorerManagers() {
		return explorerManagers;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public SimulationState getState() {
		return state;
	}

	public void setState(SimulationState state) {
		this.state = state;
	}

	public boolean isRunning() {
		return state.equals(SimulationState.RUNNING);
	}

	public boolean isReady() {
		return state.equals(SimulationState.READY);
	}

	public boolean isPaused() {
		return state.equals(SimulationState.PAUSED);
	}

	public boolean isOver() {
		return state.equals(SimulationState.OVER);
	}
}