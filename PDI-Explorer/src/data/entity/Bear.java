package data.entity;

import process.visitor.EntityVisitor;

/**
 * Instantiable class of a bear. The bear has a large territory, and massive damage/health.
 *
 */
public class Bear extends Animal  {

	private final static double SIZE_X = 2.0;
	private final static double SIZE_Y = 1.0;
	
	private final static int MAX_HEALTH = 25;
	private final static int SPEED = 5;
	private final static int DAMAGE = 5;
	private final static int SCOPE = 3;

	/**
	 * Constructor
	 * 
	 * @param position
	 */
	public Bear(double[] position) {
		super("Bear", position, new double[] {SIZE_X, SIZE_Y}, MAX_HEALTH, SPEED, DAMAGE, SCOPE);
	}

	@Override
	public <T> T accept(EntityVisitor<T> visitor) {
		return visitor.visit(this);
	}

}