package environmentcreation.mapcreation;

/**
 * Calls every border sub maps generation methods.
 * @author Léo
 *
 */
public class BorderGenerator {

	/**
	 * Calls every function for the border of the map's generation
	 * 
	 * @param subMaps The submaps to generate the borders on
	 */
	public static void generation(SubMap[][] subMaps) {
		TopBorderGenerator.generation(subMaps);
		RightBorderGenerator.generation(subMaps);
		BottomBorderGenerator.generation(subMaps);
		LeftBorderGenerator.generation(subMaps);
	}
	
}
