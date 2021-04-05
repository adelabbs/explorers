package environmentcreation.mapcreation;


/**
 * Generates all the submaps after creating the border ones. It will fill depeding on the other submaps created.
 * 
 * @author Léo
 *
 */
public class SubMapsFiller {

	/**
	 * Fills the map after having created the borders.
	 * 
	 * @param subMaps The SubMap table to fill.
	 */
	public static void fill(SubMap[][] subMaps) {
		for(int i = 2; i < 4; i ++)
			for(int j = 2; j < 4; j ++)
				subMaps[i][j] = new SubMap(7, 7, 7, 7);	
		topLeft(subMaps);
		topRight(subMaps);
		bottomRight(subMaps);
		bottomLeft(subMaps);
		lastNonPremadeSubMaps(subMaps);
		end(subMaps);
	}
	
	/**
	 * Creates the last four SubMaps.
	 * 
	 * @param subMaps The SubMap table to fill.
	 */
	private static void end(SubMap[][] subMaps) {
		last(subMaps, 1, 3);
		last(subMaps, 3, 4);
		last(subMaps, 4, 3);
		last(subMaps, 3, 1);
	}
	
	/**
	 * Creates a SubMap depending on the four surrounding.
	 * 
	 * @param subMaps The SubMap table to fill.
	 * @param i The line coordinates.
	 * @param j The columns coordinates.
	 */
	private static void last(SubMap[][] subMaps, int i, int j) {
		int top = subMaps[i-1][j].getBottom();
		int right = subMaps[i][j+1].getLeft();
		int bottom = subMaps[i+1][j].getTop();
		int left = subMaps[i][j-1].getRight();
		subMaps[i][j] = new SubMap(top, right, bottom, left);
	}
	
	/**
	 * Generates a SubMap at coordinates (1,1).
	 * 
	 * @param subMaps The SubMap table to fill.
	 */
	private static void topLeft(SubMap[][] subMaps) {
		int top = subMaps[0][1].getBottom();
		int left = subMaps[1][0].getRight();
		int right = top%2*4+3;
		int bottom = left%2*4+3; 
		subMaps[1][1] = new SubMap(top, right, bottom, left);
	}
	
	/**
	 * Generates a SubMap at coordinates (1,4).
	 * 
	 * @param subMaps The SubMap table to fill.
	 */
	private static void topRight(SubMap[][] subMaps) {
		int top = subMaps[0][4].getBottom();
		int right = subMaps[1][5].getLeft();
		int left = 3;
		if(top >= 4)
			left += 4;
		int bottom = 6+right%2;
		subMaps[1][4] = new SubMap(top, right, bottom, left);
	}
	
	/**
	 * Generates a SubMap at coordinates (4,4).
	 * 
	 * @param subMaps The SubMap table to fill.
	 */
	private static void bottomRight(SubMap[][] subMaps) {
		int right = subMaps[4][5].getLeft();
		int bottom = subMaps[5][4].getTop();
		int top = 6;
		if(right >= 4)
			top += 1;
		int left = 6;
		if(bottom >= 4)
			left += 1;
		subMaps[4][4] = new SubMap(top, right, bottom, left);
	}
	
	/**
	 * Generates a SubMap at coordinates (4,1).
	 * 
	 * @param subMaps The SubMap table to fill.
	 */
	private static void bottomLeft(SubMap[][] subMaps) {
		int bottom = subMaps[5][1].getTop();
		int left = subMaps[4][0].getRight();
		int right = bottom%2+6;
		int top = 3; 
		if(left >= 4)
			top += 4;
		subMaps[4][1] = new SubMap(top, right, bottom, left);
	}
	
	/**
	 * Generates the last SubMaps which are not made and not fully depending on the ones around. 
	 * 
	 * @param subMaps The SubMap table to fill.
	 */
	private static void lastNonPremadeSubMaps(SubMap[][] subMaps) {
		int top, right, bottom, left;
		
		top = subMaps[0][2].getBottom();
		left = subMaps[1][1].getRight();
		bottom = subMaps[2][2].getTop();
		right = top%2*4 + bottom%2 + (int)(Math.random()*2)*2;
		subMaps[1][2] = new SubMap(top, right, bottom, left);
		
		top = subMaps[1][1].getBottom();
		right = subMaps[2][2].getLeft();
		left = subMaps[2][0].getRight();
		bottom = left%2*4 + right%2 + (int)(Math.random()*2)*2;
		subMaps[2][1] = new SubMap(top, right, bottom, left);
		
		top = subMaps[3][2].getBottom();
		left = subMaps[4][1].getRight();
		bottom = subMaps[5][2].getTop();
		right = top%2*4 + bottom%2 + (int)(Math.random()*2)*2;
		subMaps[4][2] = new SubMap(top, right, bottom, left);
		
		top = subMaps[1][4].getBottom();
		right = subMaps[2][5].getLeft();
		left = subMaps[2][3].getRight();
		bottom = left%2*4 + right%2 + (int)(Math.random()*2)*2;
		subMaps[2][4] = new SubMap(top, right, bottom, left);
	}
	
}
