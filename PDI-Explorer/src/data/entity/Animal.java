package data.entity;

public abstract class Animal extends LivingEntity {

	public Animal(String type, double[] position, double[] size, int maxHealth, int speed, int damage, int scope) {
		super(type, position, size, maxHealth, speed, damage, scope);
	}

}
