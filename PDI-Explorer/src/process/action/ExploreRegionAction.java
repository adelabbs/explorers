package process.action;

import data.entity.LivingEntity;
import data.map.Point;
import data.map.Region;
import data.simulation.Environment;
import process.manager.ExplorerManager;
import process.manager.RegionManager;
import process.strategy.RegionStrategy;

public class ExploreRegionAction extends MoveAction {

	private RegionStrategy regionStrategy;
	private RegionManager regionManager;
	private ExplorerManager manager;

	public ExploreRegionAction(Environment environment, RegionStrategy regionStrategy, RegionManager regionManager) {
		super(environment);
		this.regionStrategy = regionStrategy;
		manager = regionStrategy.getExplorerManager();
		this.regionManager = regionManager;
	}

	@Override
	public void execute() {
		int direction = pickRandomDirection();
		double nextPosition[] = getNextPosition(manager.getExplorer(), direction);
		if (isValid(nextPosition)) {
			if (isOutOfCurrentRegion(nextPosition)) {
				//System.out.println("getting  out of region");
				regionManager.enter(regionStrategy);
			}else {
				super.execute();
			}
		}
	}

	private boolean isOutOfCurrentRegion(double[] nextPosition) {
		double nextPositionY = nextPosition[0];
		double nextPositionX = nextPosition[1];
		Region currentRegion = regionManager.getRegion();
		Point currentRegionTopLeft = currentRegion.getTopLeft();
		Point currentRegionBottomRight = currentRegion.getBottomRight();

		return nextPositionX < currentRegionTopLeft.getX() || nextPositionY < currentRegionTopLeft.getY()
				|| nextPositionX > currentRegionBottomRight.getX() || nextPositionY > currentRegionBottomRight.getY();
	}

	@Override
	public boolean isOver() {
		return true;
	}

	@Override
	public LivingEntity getEntity() {
		return regionStrategy.getExplorerManager().getExplorer();
	}

}