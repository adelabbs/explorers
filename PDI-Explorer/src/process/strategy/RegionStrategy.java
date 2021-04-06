package process.strategy;

import java.util.ArrayList;

import data.entity.Chest;
import data.entity.Entity;
import data.simulation.Environment;
import process.RegionManager;
import process.Simulation;
import process.action.CollectChestAction;
import process.action.ExploreRegionAction;
import process.manager.ExplorerManager;

public class RegionStrategy extends AllRounderStrategy {

	private Simulation simulation;
	private RegionManager regionManager;

	public RegionStrategy(Simulation simulation, ExplorerManager explorerManager) {
		super(explorerManager);
		this.simulation = simulation;
		regionManager = simulation.getRegionManager(explorerManager.getExplorerPosition());
		regionManager.enter(this);
	}

	@Override
	public void decide() {
		ArrayList<Entity> inScopeObstacles = getInScopeObstacles();
		if (!inScopeObstacles.isEmpty()) {
			for (Entity e : inScopeObstacles) {
				if (e.getType().equals("Chest")) {
					super.planAction(new CollectChestAction((Chest) e, getExplorerManager().getExplorer()));

				}
			}
		} else {
			super.planAction(new ExploreRegionAction(Environment.getInstance(), this, regionManager));
		}
	}

	public Simulation getSimulation() {
		return simulation;
	}

	public RegionManager getRegionManager() {
		return regionManager;
	}

	public void setRegionManager(RegionManager regionManager) {
		this.regionManager = regionManager;
	}

}