package data.entity;

public class Chest extends Entity {
	
	static final double CHEST_SIZE_X = 1.0;
	static final double CHEST_SIZE_Y = 1.0;	
	
	public Chest(double[] position) {
		super("chest", position, new double[]{CHEST_SIZE_X, CHEST_SIZE_Y});
	}	
	
}
