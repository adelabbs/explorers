package data.message;

import data.entity.Explorer;

public class HelpMessage extends Message<double[]> {

	public HelpMessage(double[] content, Explorer explorer) {
		super(content, explorer);
		setSendingTime(10);
	}
	
}
