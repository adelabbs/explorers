package data.message;

import data.entity.Explorer;
import data.map.ExplorerMap;
import data.map.GeneralExplorerMap;
import data.simulation.Environment;
import process.action.utility.MapMerger;

public class MapMessage extends Message<ExplorerMap> {
	
	public MapMessage(ExplorerMap content, Explorer explorer) {
		super(content, explorer);
		setSendingTime(1000);
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
		try {
			Thread.sleep(getSendingTime());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gem.setUsed(false);
	}
	
}