package environmentcreation;

import java.util.HashMap;

import data.map.Point;
import data.map.Region;

/**
 * The region creation class. Provides a method to create a HashMap of all the
 * regions in the environment.
 * 
 * @author Adel
 *
 */
public class RegionsCreator {
	private static Integer nextId = 0;
	public static final int REGIONS_COUNT_X = 9;
	public static final int REGIONS_COUNT_Y = 9;
	public static final int MAP_LENGTH = 90;

	public static HashMap<Integer, Region> creation() {
		return creation(MAP_LENGTH, MAP_LENGTH, REGIONS_COUNT_X, REGIONS_COUNT_Y);
	}

	public static HashMap<Integer, Region> creation(int mapWidth, int mapHeight, int regionCountX, int regionCountY) {
		HashMap<Integer, Region> regions = new HashMap<Integer, Region>();
		int regionWidth = mapWidth / regionCountX;
		int regionHeight = mapHeight / regionCountY;
		for (int y = 0; y < regionCountY; y++) {
			for (int x = 0; x < regionCountX; x++) {
				Point topLeft = new Point(x * regionWidth, y * regionHeight);
				Point bottomRight = new Point(topLeft.getX() + regionWidth - 1, topLeft.getY() + regionHeight - 1);
				Region region = new Region(getNextId(), topLeft, bottomRight);
				regions.put(region.getId(), region);
			}
		}
		return regions;
	}

	private static Integer getNextId() {
		return ++nextId;
	}

}