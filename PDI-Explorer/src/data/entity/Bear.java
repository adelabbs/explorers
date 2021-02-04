package data.entity;

import process.EntityVisitor;

public class Bear extends LivingEntity {

	public Bear(String type, double[] position, double[] size, int maxHealth, int speed, int damage, int scope) {
		super(type, position, size, maxHealth, speed, damage, scope);
	}

	@Override
	public <T> T accept(EntityVisitor<T> visitor) {
		return visitor.visit(this);
	}

}