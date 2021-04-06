package tests.manual;

import java.util.HashMap;

import data.map.Point;
import data.map.Region;
import environmentcreation.RegionsCreator;

public class TestRegions {

	public static void main(String[] args) {
		HashMap<Integer, Region> regions = RegionsCreator.creation(90, 90, 6, 6);
		for (Region region : regions.values()) {
			Point topLeft = region.getTopLeft();
			Point bottomRight = region.getBottomRight();
			System.out.println("Region " + region.getId());
			System.out.println(topLeft);
			System.out.println(bottomRight);
		}

		double positionX = 61;
		double positionY = 75;
		Integer id = 1 + ((int) (positionY / 15) * 6 + (int) (positionX / 15));
		System.out.println("Id = " + id);

	}

}
