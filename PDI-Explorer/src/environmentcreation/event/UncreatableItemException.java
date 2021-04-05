package environmentcreation.event;

/**
 * Exception needed when we create an item.
 * 
 * @author Léo
 *
 */
@SuppressWarnings("serial")
public class UncreatableItemException extends Exception {

	/**
	 * Overrides the original Exeption constructor
	 * 
	 * @param s The error message.
	 */
	public UncreatableItemException(String s) {
		super(s);
	}
	
}
