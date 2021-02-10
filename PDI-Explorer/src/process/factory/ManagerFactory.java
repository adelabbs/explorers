package process.factory;

import data.entity.Bear;
import data.entity.Explorer;
import process.Simulation;
import process.manager.BearManager;
import process.manager.ExplorerManager;
import process.strategy.CombatStrategy;
import process.strategy.GreedStrategy;
import process.strategy.RandomStrategy;
import tests.manual.comms.JustChattingStrategy;

public class ManagerFactory {
	public static final int RANDOM_STRATEGY = 0;
	public static final int DODGE_STRATEGY = 1;
	public static final int GREED_STRATEGY = 2;
	public static final int COMBAT_STRATEGY = 3;
	public static final int JUSTCHATTING_STRATEGY = 4;
	

	public static ExplorerManager createExplorerManager(Simulation simulation, Explorer explorer,
			int explorationStrategy) throws IllegalArgumentException {
		switch (explorationStrategy) {
		case COMBAT_STRATEGY:
			ExplorerManager combatExplorerManager = new ExplorerManager(simulation, explorer);
			combatExplorerManager.setStrategy(new CombatStrategy(combatExplorerManager));
			return combatExplorerManager;
		case GREED_STRATEGY:
			ExplorerManager greedExplorerManager = new ExplorerManager(simulation, explorer);
			greedExplorerManager.setStrategy(new GreedStrategy(greedExplorerManager));
			return greedExplorerManager;
		case DODGE_STRATEGY:
			ExplorerManager dodgeExplorerManager = new ExplorerManager(simulation, explorer);
			dodgeExplorerManager.setStrategy(new GreedStrategy(dodgeExplorerManager));
			return dodgeExplorerManager;
		case RANDOM_STRATEGY:
			ExplorerManager randomExplorerManager = new ExplorerManager(simulation, explorer);
			randomExplorerManager.setStrategy(new RandomStrategy(randomExplorerManager));
			return randomExplorerManager;
		case JUSTCHATTING_STRATEGY:
			ExplorerManager chattingExplorerManager = new ExplorerManager(simulation, explorer);
			chattingExplorerManager.setStrategy(new JustChattingStrategy(chattingExplorerManager));
			return chattingExplorerManager;
		default:
			throw new IllegalArgumentException("Unknown strategy: " + explorationStrategy);
		}
	}
	
	
	public static BearManager createBearManager(Simulation simulation, Bear bear) {
		return new BearManager(simulation, bear);
	}
}