package data.entity;

public class Chest extends Entity {
	
	static final double CHEST_POS_X = 1.0;
	static final double CHEST_POS_Y = 1.0;	
	
	public Chest(double[] position) {
		super("chest", position, new double[]{CHEST_POS_X, CHEST_POS_Y});
	}	
	
}
