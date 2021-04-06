package data.map;

/**
 * Contains the information of fused maps. All the maps are merged here when explorers send their map. 
 *
 */
public class GeneralExplorerMap extends ExplorerMap {

	private boolean used;
	
	/**
	 * Constructor.
	 * 
	 * @param tiles
	 */
	public GeneralExplorerMap(ExplorerTile[][] tiles) {
		super(tiles);
		used = false;
	}
	
	/**
	 * Constructor without parameters.
	 */
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
