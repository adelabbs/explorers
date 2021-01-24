package data.simulation;

@SuppressWarnings("serial")
public class ItemAlreadyExistsException extends Exception {
	public ItemAlreadyExistsException(Item item) {
		super("Item already exists : " + item.toString());
	}
}