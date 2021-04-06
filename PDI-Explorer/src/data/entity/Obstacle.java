package data.entity;

import process.visitor.EntityVisitor;

/**
 * The obstacles can't be passed by the explorers.
 *
 */
public class Obstacle extends Entity {

	/**
	 * Constructor.
	 * 
	 * @param type
	 * @param position
	 * @param size
	 */
	public Obstacle(String type, double[] position, double[] size) {
		super(type, position, size);
	}

	@Override
	public <T> T accept(EntityVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
