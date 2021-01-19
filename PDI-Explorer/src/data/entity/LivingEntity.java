package data.entity;

public class LivingEntity extends BreakableEntity {

	private int speed;
	private int damage;
	private int scope;
	
	public LivingEntity(String type, double[] position, double[] size, int maxHealth, int speed, int damage, int scope) {
		super(type, position, size, maxHealth);
		this.speed = speed;
		this.damage = damage;
		this.scope = scope;
	}

	public int getSpeed() {
		return speed;
	}

	public int getDamage() {
		return damage;
	}

	public int getScope() {
		return scope;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void setScope(int scope) {
		this.scope = scope;
	}
	
}
