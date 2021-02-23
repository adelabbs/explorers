package environmentcreation.mapcreation;

/**
 * Calls every border sub maps generation methods.
 * @author Léo
 *
 */
public class BorderGenerator {

	public static void generation(SubMap[][] subMaps) {
		TopBorderGenerator.generation(subMaps);
		RightBorderGenerator.generation(subMaps);
		BottomBorderGenerator.generation(subMaps);
		LeftBorderGenerator.generation(subMaps);
	}
	
}
