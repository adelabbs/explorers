package process.manager;

import data.map.Point;
import data.map.Region;
import process.strategy.RegionStrategy;

/**
 * The region processing class. This limits access to a given region to a
 * maximum of 1 explorer at at time.
 * 
 * @author Adel
 *
 */
public class RegionManager {
	private Region region;
	private RegionStrategy occupyingExplorer = null;

	public static final int TIMEOUT = 3000;

	public RegionManager(Region region) {
		this.region = region;
	}

	public synchronized void enter(RegionStrategy regionStrategy, double position[]) {
		if (occupyingExplorer != null) {
			regionStrategy.updateExplorerPosition(position);
			try {
				wait(TIMEOUT);
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}

		// The explorer leaves and frees its previous region.
		RegionManager previousRegionManager = regionStrategy.getRegionManager();
		previousRegionManager.exit();

		// The explorer enters into this region.
		regionStrategy.setRegionManager(this);
		occupyingExplorer = regionStrategy;
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

	public String getOccupyingExplorerName() {
		return occupyingExplorer.getExplorerManager().getExplorer().getName();
	}

	public Point getBottomRight() {
		return region.getBottomRight();
	}
}