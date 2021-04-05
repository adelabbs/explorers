package process.factory;

import data.entity.Bear;
import data.entity.Explorer;
import process.Simulation;
import process.manager.BearManager;
import process.manager.ExplorerManager;
import process.strategy.AllRounderStrategy;
import process.strategy.RandomStrategy;
import process.strategy.RegionStrategy;

public class ManagerFactory {
	public static final int RANDOM_STRATEGY = 0;
	public static final int ALL_ROUNDER_STRATEGY = 1;
	public static final int REGION_STRATEGY = 2;

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
		case REGION_STRATEGY:
			ExplorerManager regionExplorerManager = new ExplorerManager(simulation, explorer);
			regionExplorerManager.setStrategy(new RegionStrategy(regionExplorerManager));
			return regionExplorerManager;
		default:
			throw new IllegalArgumentException("Unknown strategy: " + explorationStrategy);
		}
	}

	public static BearManager createBearManager(Simulation simulation, Bear bear) {
		return new BearManager(simulation, bear);
	}
}