package data.entity;

/**
 * Class handling every animals in the simulation. The initPos permits the calculation of the animal's territory.
 *
 */
public abstract class Animal extends LivingEntity {
	
	private double[] initPosition;

	/**
	 * Constructor
	 * 
	 * @param type
	 * @param position 
	 * @param size
	 * @param maxHealth
	 * @param speed
	 * @param damage
	 * @param scope
	 */
	public Animal(String type, double[] position, double[] size, int maxHealth, int speed, int damage, int scope) {
		super(type, position, size, maxHealth, speed, damage, scope);
		this.initPosition = new double[] {position[0], position[1]};
	}

	public double[] getInitPosition() {
		return  initPosition;
	}
	
	public void setInitPosition(double[] initPosition) {
		this.initPosition = initPosition;
	}
	
}
