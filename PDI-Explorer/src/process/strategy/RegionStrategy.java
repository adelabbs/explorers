package process.strategy;

import data.simulation.Environment;
import process.RegionManager;
import process.action.ExploreRegionAction;
import process.manager.ExplorerManager;

public class RegionStrategy extends AllRounderStrategy {

	private RegionManager regionManager;

	public RegionStrategy(ExplorerManager explorerManager) {
		super(explorerManager);
	}

	@Override
	public void decide() {
		super.planAction(new ExploreRegionAction(Environment.getInstance(), this, regionManager));
	}

	public RegionManager getRegionManager() {
		return regionManager;
	}

	public void setRegionManager(RegionManager regionManager) {
		this.regionManager = regionManager;
	}

}