package environmentcreation.mapcreation;

/**
 * First used function, creates the corner of a SubMap table.
 * 
 * @author Léo
 *
 */
public class CornerGenerator {

	/**
	 * Calls all the function to generate the corners of a SubMap table.
	 * 
	 * @param subMaps The SubMap table to generate.
	 */
	public static void generation(SubMap[][] subMaps) {
		subMaps[0][0] = topLeft();
		subMaps[0][5] = topRight();
		subMaps[5][0] = bottomLeft();
		subMaps[5][5] = bottomRight();
	}
	
	/**
	 * Generates the top left corner.
	 * 
	 * @return The generated SubMap.
	 */
	private static SubMap topLeft() {
		return new SubMap(0, OTGeneration(), OTGeneration(), 0);
	}
	
	/**
	 * Generates the top right corner.
	 * 
	 * @return The generated SubMap.
	 */
	private static SubMap topRight() {
		return new SubMap(0, 0, FSGeneration(), OTGeneration());
	}
	
	/**
	 * Generates the bottom left corner.
	 * 
	 * @return The generated SubMap.
	 */
	private static SubMap bottomLeft() {
		return new SubMap(OTGeneration(), FSGeneration(), 0, 0);
	}
	
	/**
	 * Generates the bottom right corner.
	 * 
	 * @return The generated SubMap.
	 */
	private static SubMap bottomRight() {
		return new SubMap(FSGeneration(), 0, 0, FSGeneration());
	}
	
	/**
	 * Generates a code, one or three, used for corner generation.
	 * 
	 * @return The code of the layer.
	 */
	private static int OTGeneration() {
		return (int)(Math.random()*2)*2+1;
	}
	
	/**
	 * Generates a code, four or six, used for corner generation.
	 * 
	 * @return The code of the layer.
	 */
	private static int FSGeneration() {
		if((int)(Math.random()*2) == 0)
			return 4;
		else
			return 6;
	}
	
}
