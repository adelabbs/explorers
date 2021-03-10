package data.communication;

import data.entity.Explorer;
import data.map.ExplorerMap;

public class MapMessage extends Message<ExplorerMap> {
	
	public MapMessage(ExplorerMap content, Explorer explorer) {
		super(content, explorer);
		setSendingTime(150);
	}
	
}