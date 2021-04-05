package process;

import data.map.Point;
import data.map.Region;
import process.strategy.RegionStrategy;

public class RegionManager {
	private Region region;
	private RegionStrategy occupyingExplorer = null;

	public RegionManager(Region region) {
		this.region = region;
	}

	public synchronized void enter(RegionStrategy explorerStrategy) {
		if (occupyingExplorer != null) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}

		// The explorer leaves and frees its previous region.
		RegionManager previousRegionManager = explorerStrategy.getRegionManager();
		previousRegionManager.exit();

		// The explorer enters into this region.
		explorerStrategy.setRegionManager(this);
		occupyingExplorer = explorerStrategy;
	}

	public synchronized void exit() {
		occupyingExplorer = null;
		notify();
	}

	public Region getRegion() {
		return region;
	}

	public boolean isFree() {
		return occupyingExplorer == null;
	}

	public Point getTopLeft() {
		return region.getTopLeft();
	}

	public Point getBottomRight() {
		return region.getBottomRight();
	}

}