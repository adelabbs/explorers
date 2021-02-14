package process.action;

import data.entity.Explorer;
import data.map.ExplorerMap;
import data.map.ExplorerTile;
import data.message.MapMessage;

public class UpdateMapAction implements Action {
	public static final int UPDATE_MAP_TIME = 100;
	private MapMessage message;
	private Explorer explorer;

	private int executionTime = UPDATE_MAP_TIME;

	public UpdateMapAction(MapMessage message, Explorer explorer) {
		this.message = message;
		this.explorer = explorer;
	}

	@Override
	public void execute() {
		executionTime--;
		if (executionTime == 0) {
			ExplorerMap messageMap = message.getMap();
			ExplorerMap currentMap = explorer.getMap();
			int length = currentMap.getTiles().length;
			for (int y = 0; y < length; y++) {
				for (int x = 0; x < length; x++) {
					ExplorerTile messageMapTile = messageMap.getTile(y, x);
					ExplorerTile currentMapTile = currentMap.getTile(y, x);
					if (messageMapTile != null) {
						int interest = messageMapTile.getInterest();
						if (interest > 0) {
							currentMapTile.setInterest(interest);
						}
					}
				}
			}
		}
	}

	@Override
	public boolean isOver() {
		return executionTime <= 0;
	}

	public MapMessage getMessage() {
		return message;
	}

	public Explorer getExplorer() {
		return explorer;
	}

	public int getExecutionTime() {
		return executionTime;
	}

}
