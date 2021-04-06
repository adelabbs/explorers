package process;

import data.map.Point;
import data.map.Region;
import process.strategy.RegionStrategy;

public class RegionManager {
	private Region region;
	private RegionStrategy occupyingExplorer = null;

	public static final int TIMEOUT = 5000;

	public RegionManager(Region region) {
		this.region = region;
	}

	public synchronized void enter(RegionStrategy explorerStrategy) {
		if (occupyingExplorer != null) {
			try {
				double pos[] = explorerStrategy.getExplorerManager().getExplorerPosition();
				System.out.println("Explorer at [x = " + pos[1] + " y = " + pos[0] + "] tries to enter new region ");
				System.out.println("Region = topLeft :" + region.getTopLeft() + " br = " + region.getBottomRight());
				wait(TIMEOUT);
				explorerStrategy.stayInCurrentRegion();
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

	public String getOccupyingExplorerName() {
		return occupyingExplorer.getExplorerManager().getExplorer().getName();
	}

	public Point getBottomRight() {
		return region.getBottomRight();
	}

}