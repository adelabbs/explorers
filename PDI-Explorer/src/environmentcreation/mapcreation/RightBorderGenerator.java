package environmentcreation.mapcreation;

public class RightBorderGenerator {

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
	
	private static int leftGenerator(int top) {
		int res = (int)(Math.random()*4);
		if(res == 4)
			res = 3;
		if(top >= 4)
			return res + 4;
		else
			return res;
	}
	
	private static int bottomGenerator(int left) {
		int res = (int)(Math.random()*2)*2;
		if(left%2 == 1)
			return res + 4;
		else
			return res;
	}
	
	private static int lastLeftGenerator(int top, int bottom) {
		int res = (int)(Math.random()*2)*2;
		if(top >= 4)
			res += 4;
		if(bottom >= 4)
			res += 1;
		return res;
	}
	
}
