package environmentcreation.mapcreation;

/**
 * Generation of the sub maps on the right border.
 * @author Léo
 *
 */
public class RightBorderGenerator {

	/**
	 * Creates all the right border's submaps.
	 * 
	 * @param subMaps The submap to generate the right border on.
	 */
	public static void generation(SubMap[][] subMaps) {
		int top, left, bottom;
		for(int i = 1; i < 4; i ++) {
			top = subMaps[i-1][5].getBottom();
			left = leftGenerator(top);
			bottom = bottomGenerator(left);
			subMaps[i][5] = new SubMap(top, 0, bottom, left);
		}
		top = subMaps[3][5].getBottom();
		bottom = subMaps[5][5].getTop();
		left = lastLeftGenerator(top, bottom);
		subMaps[4][5] = new SubMap(top, 0, bottom, left);
	}
	
	/**
	 * Generates the left layer's code of a SubMap.
	 * 
	 * @param top The code of the top layer of the SubMap.
	 * @return The code of the left layer.
	 */
	private static int leftGenerator(int top) {
		int res = (int)(Math.random()*4);
		if(res == 4)
			res = 3;
		if(top >= 4)
			return res + 4;
		else
			return res;
	}
	
	/**
	 * Generates the bottom layer's code of a SubMap.
	 * 
	 * @param left The code of the left layer of the SubMap.
	 * @return The code of the bottom layer.
	 */
	private static int bottomGenerator(int left) {
		int res = (int)(Math.random()*2)*2;
		if(left%2 == 1)
			return res + 4;
		else
			return res;
	}
	
	/**
	 * Generates the last SubMap left layer's code.
	 * 
	 * @param top The code of the top layer of the SubMap.
	 * @param bottom The code of the bottom layer of the SubMap.
	 * @return The code of the left layer.
	 */
	private static int lastLeftGenerator(int top, int bottom) {
		int res = (int)(Math.random()*2)*2;
		if(top >= 4)
			res += 4;
		if(bottom >= 4)
			res += 1;
		return res;
	}
	
}
