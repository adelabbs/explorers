package data.message;

import data.entity.Explorer;

/**
 * Asks for help with the coordinates of the explorer.
 *
 */
public class HelpMessage extends Message<double[]> {

	/**
	 * Constructor.
	 * 
	 * @param content
	 * @param explorer
	 */
	public HelpMessage(double[] content, Explorer explorer) {
		super(content, explorer);
		setSendingTime(10);
	}

	@Override
	public void send() {
		
	}
	
}
