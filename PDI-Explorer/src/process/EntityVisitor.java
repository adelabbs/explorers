package process;

import data.entity.Bear;
import data.entity.Chest;
import data.entity.Entity;
import data.entity.Explorer;
import data.entity.Obstacle;

/**
 * Generic visitor supporting all concrete {@link Entity} types.
 *
 */
public interface EntityVisitor<T> {
	T visit(Chest entity);

	T visit(Obstacle entity);

	T visit(Explorer entity);

	T visit(Bear entity);
}
