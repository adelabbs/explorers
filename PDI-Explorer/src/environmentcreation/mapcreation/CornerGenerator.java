package environmentcreation.mapcreation;

public class CornerGenerator {

	public static void generation(SubMap[][] subMaps) {
		subMaps[0][0] = topLeft();
		subMaps[0][5] = topRight();
		subMaps[5][0] = bottomLeft();
		subMaps[5][5] = bottomRight();
	}
	
	private static SubMap topLeft() {
		return new SubMap(0, OTGeneration(), OTGeneration(), 0);
	}
	
	private static SubMap topRight() {
		return new SubMap(0, 0, FSGeneration(), OTGeneration());
	}
	
	private static SubMap bottomLeft() {
		return new SubMap(OTGeneration(), FSGeneration(), 0, 0);
	}
	
	private static SubMap bottomRight() {
		return new SubMap(FSGeneration(), 0, 0, FSGeneration());
	}
	
	private static int OTGeneration() {
		return (int)(Math.random()*2)*2+1;
	}
	private static int FSGeneration() {
		if((int)(Math.random()*2) == 0)
			return 4;
		else
			return 6;
	}
	
}
