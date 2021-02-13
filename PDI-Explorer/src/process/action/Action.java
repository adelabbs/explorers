package process.action;

/**
 * Each concrete {@link Action} contains its own implementation. The necessary
 * data for the execute method can be provided using the constructor or via
 * setter injection.
 *
 */
public interface Action {
	void execute();
	boolean isOver();
}