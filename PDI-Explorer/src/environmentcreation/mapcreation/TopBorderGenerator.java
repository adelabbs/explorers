package environmentcreation.mapcreation;

/**
 * Generation of the sub maps on the top border.
 * @author Léo
 *
 */
public class TopBorderGenerator {

	/**
	 * Creates all the top border's submaps.
	 * 
	 * @param subMaps The submap to generate the top border on.
	 */
	public static void generation(SubMap[][] subMaps) {
		int left, bottom, right;
		for(int i = 1; i < 4; i ++) {
			left = subMaps[0][i-1].getRight();
			bottom = bottomGenerator(left);
			right = rightGenerator(bottom);
			subMaps[0][i] = new SubMap(0, right, bottom, left);
		}
		left = subMaps[0][3].getRight();
		right = subMaps[0][5].getLeft();
		bottom = lastBottomGenerator(left, right);
		subMaps[0][4] = new SubMap(0, right, bottom, left);
	}
	
	/**
	 * Generates the bottom layer's code of a SubMap.
	 * 
	 * @param left The code of the left layer of the SubMap.
	 * @return The code of the bottom layer.
	 */
	private static int bottomGenerator(int left) {
		int res = (int)(Math.random()*4);
		if(res == 4)
			res = 3;
		if(left%2 == 1)
			return res + 4;
		else
			return res;
	}
	
	/**
	 * Generates the right layer's code of a SubMap.
	 * 
	 * @param bottom The code of the bottom layer of the SubMap.
	 * @return The code of the top layer.
	 */
	private static int rightGenerator(int bottom) {
		int res = (int)(Math.random()*2)*2;
		if(bottom%2 == 1)
			return res + 1;
		else
			return res;
	}
	
	/**
	 * Generates the last SubMap bottom layer's code.
	 * 
	 * @param left The code of the left layer of the SubMap.
	 * @param right The code of the right layer of the SubMap.
	 * @return The code of the bottom layer.
	 */
	private static int lastBottomGenerator(int left, int right) {
		return (left%2*4) + (int)(Math.random()*2)*2 + (right%2);
	}
	
}
