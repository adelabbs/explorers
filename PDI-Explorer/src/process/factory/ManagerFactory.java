package process.factory;

import data.entity.Explorer;
import environmentcreation.ExplorationStrategy;
import process.ExplorerManager;
import process.Simulation;

public class ManagerFactory {
	public static ExplorerManager createExplorerManager(Simulation simulation, Explorer explorer, ExplorationStrategy explorationStrategy) {
		return new ExplorerManager(simulation, explorer, explorationStrategy);
	}
}