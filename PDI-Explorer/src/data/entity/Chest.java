package data.entity;

import process.visitor.EntityVisitor;

/**
 * Chests are the explorer's goal. They will try to find and collect these during the simulation.
 * @author Léo
 *
 */
public class Chest extends Entity {

	static final double CHEST_SIZE_X = 1.0;
	static final double CHEST_SIZE_Y = 1.0;

	public Chest(double[] position) {
		super("Chest", position, new double[] { CHEST_SIZE_X, CHEST_SIZE_Y });
	}

	@Override
	public <T> T accept(EntityVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
