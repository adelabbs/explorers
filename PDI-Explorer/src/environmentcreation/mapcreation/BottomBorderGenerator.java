package environmentcreation.mapcreation;

/**
 * Generation of the sub maps on the bottom border.
 * @author Léo
 *
 */
public class BottomBorderGenerator {

	public static void generation(SubMap[][] subMaps) {
		int top, right, left;
		for(int i = 1; i < 4; i ++) {
			left = subMaps[5][i-1].getRight();
			top = topGenerator(left);
			right = rightGenerator(top);
			subMaps[5][i] = new SubMap(top, right, 0, left);
		}
		left = subMaps[5][3].getRight();
		right = subMaps[5][5].getLeft();
		top = lastTopGenerator(left, right);
		subMaps[5][4] = new SubMap(top, right, 0, left);
	}
	
	private static int topGenerator(int left) {
		int res = (int)(Math.random()*4);
		if(res == 4)
			res = 3;
		if(left >= 4)
			return res + 4;
		else
			return res;
	}
	
	private static int rightGenerator(int top) {
		int res = (int)(Math.random()*2)*2;
		if(top%2 == 1)
			return res + 4;
		else
			return res;
	}
	
	private static int lastTopGenerator(int left, int right) {
		int res = (int)(Math.random()*2)*2;
		if(left >= 4)
			res += 4;
		if(right >= 4)
			res += 1;
		return res;
	}
	
}
