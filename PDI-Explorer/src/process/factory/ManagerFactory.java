package process.factory;

import data.entity.Bear;
import data.entity.Explorer;
import process.Simulation;
import process.manager.BearManager;
import process.manager.ExplorerManager;
import process.strategy.AllRounderStrategy;
import process.strategy.RandomStrategy;

/** 
 * 
 * Factory for strategies
 *
 */
public class ManagerFactory {
	public static final int RANDOM_STRATEGY = 0;
	public static final int ALL_ROUNDER_STRATEGY = 1;

	/**
	 * 
	 * @param simulation
	 * @param explorer
	 * @param explorationStrategy
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static ExplorerManager createExplorerManager(Simulation simulation, Explorer explorer,
			int explorationStrategy) throws IllegalArgumentException {
		switch (explorationStrategy) {
		case RANDOM_STRATEGY:
			ExplorerManager randomExplorerManager = new ExplorerManager(simulation, explorer);
			randomExplorerManager.setStrategy(new RandomStrategy(randomExplorerManager));
			return randomExplorerManager;
		case ALL_ROUNDER_STRATEGY:
			ExplorerManager allRounderExplorerManager = new ExplorerManager(simulation, explorer);
			allRounderExplorerManager.setStrategy(new AllRounderStrategy(allRounderExplorerManager));
			return allRounderExplorerManager;
		default:
			throw new IllegalArgumentException("Unknown strategy: " + explorationStrategy);
		}
	}

	/**
	 * 
	 * @param simulation
	 * @param bear
	 * @return
	 */
	public static BearManager createBearManager(Simulation simulation, Bear bear) {
		return new BearManager(simulation, bear);
	}
}