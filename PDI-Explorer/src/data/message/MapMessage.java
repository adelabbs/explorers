package data.message;

import data.entity.Explorer;
import data.map.ExplorerMap;
import data.map.GeneralExplorerMap;
import data.simulation.Environment;
import process.action.utility.MapMerger;

public class MapMessage extends Message<ExplorerMap> {
	
	public MapMessage(ExplorerMap content, Explorer explorer) {
		super(content, explorer);
		setSendingTime(150);
	}

	@Override
	public void send() {
		GeneralExplorerMap gem = Environment.getInstance().getGeneralExplorerMap();
		while(gem.isUsed())
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		gem.setUsed(true);
		MapMerger.merge(getExplorer().getMap().getTiles());
		gem.setUsed(false);
	}
	
}