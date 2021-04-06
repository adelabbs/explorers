package data.message;

import data.entity.Explorer;

/**
 * The abstract class for messages.
 *
 * @param <T> The type of content in the message.
 */
public abstract class Message<T> {
	
	private T content;
	private Explorer explorer;
	private int sendingTime;
	
	/**
	 * Constructor.
	 * 
	 * @param content
	 * @param explorer
	 */
	public Message(T content, Explorer explorer) {
		this.content = content;
		this.explorer = explorer;
	}
	
	/**
	 * Sends the message, depending on its type.
	 */
	public abstract void send();

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public int getSendingTime() {
		return sendingTime;
	}

	public void setSendingTime(int sendingTime) {
		this.sendingTime = sendingTime;
	}
	
	public Explorer getExplorer() {
		return explorer;
	}

	public void setExplorer(Explorer explorer) {
		this.explorer = explorer;
	}
	
}
