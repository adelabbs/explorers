package environmentcreation.mapcreation;

public class BorderGenerator {

	public static void generation(SubMap[][] subMaps) {
		TopBorderGenerator.generation(subMaps);
		RightBorderGenerator.generation(subMaps);
		BottomBorderGenerator.generation(subMaps);
		LeftBorderGenerator.generation(subMaps);
	}
	
}
