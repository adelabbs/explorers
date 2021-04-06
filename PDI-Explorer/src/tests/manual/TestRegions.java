package tests.manual;

import java.util.HashMap;

import data.map.Point;
import data.map.Region;
import environmentcreation.RegionsCreator;

public class TestRegions {

	public static void main(String[] args) {
		HashMap<Integer, Region> regions = RegionsCreator.creation(90, 90, 9, 9);
		for (Region region : regions.values()) {
			Point topLeft = region.getTopLeft();
			Point bottomRight = region.getBottomRight();
			System.out.println("Region " + region.getId());
			System.out.println(topLeft);
			System.out.println(bottomRight);
		}

		double positionX = 61;
		double positionY = 75;
		System.out.println("map = " + (RegionsCreator.MAP_LENGTH / RegionsCreator.REGIONS_COUNT_Y));
		Integer id = 1 + ((int) (positionY / (RegionsCreator.MAP_LENGTH / RegionsCreator.REGIONS_COUNT_Y))
				* RegionsCreator.REGIONS_COUNT_Y
				+ (int) (positionX / (RegionsCreator.MAP_LENGTH / RegionsCreator.REGIONS_COUNT_X)));
		System.out.println("Id = " + id);

	}

}
