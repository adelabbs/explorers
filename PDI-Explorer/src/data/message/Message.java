package data.message;

import process.visitor.MessageVisitor;

/**
 * The abstract message type.
 *
 */
public abstract class Message {
	String message;

	public Message(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return message;
	}

	public abstract <T> T accept(MessageVisitor<T> visitor);

}