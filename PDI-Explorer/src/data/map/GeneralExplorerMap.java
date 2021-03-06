package data.map;

public class GeneralExplorerMap extends ExplorerMap {

	private boolean used;
	
	public GeneralExplorerMap(ExplorerTile[][] tiles) {
		super(tiles);
		used = false;
	}
	
	public GeneralExplorerMap() {
		super(new ExplorerTile[90][90]);
		used = false;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}
	
}
