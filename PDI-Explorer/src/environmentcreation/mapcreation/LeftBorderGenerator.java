package environmentcreation.mapcreation;

public class LeftBorderGenerator {

	public static void generation(SubMap[][] subMaps) {
		int top, right, bottom;
		for(int i = 1; i < 4; i ++) {
			top = subMaps[i-1][0].getBottom();
			right = rightGenerator(top);
			bottom = bottomGenerator(right);
			subMaps[i][0] = new SubMap(top, right, bottom, 0);
		}
		top = subMaps[3][0].getBottom();
		bottom = subMaps[5][0].getTop();
		right = lastRightGenerator(top, bottom);
		subMaps[4][0] = new SubMap(top, right, bottom, 0);
	}
	
	private static int rightGenerator(int top) {
		int res = (int)(Math.random()*4);
		if(res == 4)
			res = 3;
		if(top%2 == 1)
			return res + 4;
		else
			return res;
	}
	
	private static int bottomGenerator(int right) {
		int res = (int)(Math.random()*2)*2;
		if(right%2 == 1)
			return res + 1;
		else
			return res;
	}
	
	private static int lastRightGenerator(int top, int bottom) {
		return (top%2*4) + (int)(Math.random()*2)*2 + (bottom%2);
	}
	
}
