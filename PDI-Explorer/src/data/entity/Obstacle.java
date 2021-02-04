package data.entity;

import process.EntityVisitor;

public class Obstacle extends Entity {

	public Obstacle(String type, double[] position, double[] size) {
		super(type, position, size);
	}

	@Override
	public <T> T accept(EntityVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
