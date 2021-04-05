package environmentcreation.event;

/**
 * Exception needed when we create an entity.
 * 
 * @author Léo
 */
@SuppressWarnings("serial")
public class EntityCreationException extends Exception {

	/**
	 * Overrides the original Exeption constructor.
	 * 
	 * @param s The error message.
	 */
	public EntityCreationException(String s) {
		super(s);
	}
	
}
