package process.strategy;

import java.util.ArrayList;

import data.entity.Chest;
import data.entity.Entity;
import data.simulation.Environment;
import process.Simulation;
import process.action.CollectChestAction;
import process.action.ExploreRegionAction;
import process.manager.ExplorerManager;
import process.manager.RegionManager;

/**
 * The region strategy processing class. The explorer behavior is naive: they
 * collect chests within their scope or move randomly. When they're about to
 * enter a new region they wait for the occupying explorer to leave the region.
 * 
 * @author Adel
 *
 */
public class RegionStrategy extends AllRounderStrategy {

	private Simulation simulation;
	private RegionManager regionManager;

	public RegionStrategy(Simulation simulation, ExplorerManager explorerManager) {
		super(explorerManager);
		this.simulation = simulation;
		regionManager = simulation.getRegionManager(explorerManager.getExplorerPosition());
		regionManager.enter(this, explorerManager.getExplorerPosition());
		System.out.println("begin region " + regionManager.getRegion().getId());
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
			super.planAction(new ExploreRegionAction(simulation, Environment.getInstance(), this, regionManager));
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

	public void updateExplorerPosition(double[] newPosistion) {
		getExplorerManager().updatePosition(newPosistion);
	}

}