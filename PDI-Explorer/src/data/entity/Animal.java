package data.entity;

/**
 * Class handling every animals in the simulation. The initPos permits the calculation of the animal's territory.
 * @author Léo
 *
 */
public abstract class Animal extends LivingEntity {
	
	private double[] initPosition;

	public Animal(String type, double[] position, double[] size, int maxHealth, int speed, int damage, int scope) {
		super(type, position, size, maxHealth, speed, damage, scope);
		this.initPosition = position;
	}

	public double[] getInitPosition() {
		return  initPosition;
	}
	
	public void setInitPosition(double[] initPosition) {
		this.initPosition = initPosition;
	}
	
}
