package process;

import java.util.ArrayList;

import data.simulation.SimulationEntry;
import process.factory.ExplorerFactory;
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

	/*
	 * At the moment a method is enough to prepare the simulation. We might need a
	 * separate Builder class later if the initial preparation work increases.
	 */
	private void buildSimulation() {
		environment = new Environment();
		int explorerCount = environment.getExplorerInit();
		environment.setExplorerInit(explorerCount);
		for (int count = 0; count < explorerCount; count++) {
			Explorer explorer = ExplorerFactory.createTestExplorer(null);
			ExplorerManager explorerManager = new ExplorerManager(explorer); // This also starts the explorerManager
																				// thread.
			explorerManagers.add(explorerManager);
		}
		setState(SimulationState.READY);
	}

	public void simulate() {
		//TODO
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

	public void setState(SimulationState state) {
		this.state = state;
	}
}