package data.map;

/**
 * Every explorer has his own ExplorerMap. It has the same size than the map, and is composed of ExplorerTiles.
 *
 */
public class ExplorerMap {

	private ExplorerTile[][] tiles = new ExplorerTile[90][90];
	
	/**
	 * Constructor.
	 * 
	 * @param tiles
	 */
	public ExplorerMap(ExplorerTile[][] tiles) {
		this.tiles = tiles;
	}

	public ExplorerTile[][] getTiles() {
		return tiles;
	}

	public ExplorerTile getTile(int i, int j) {
		return tiles[i][j];
	}
	
	public void setTiles(ExplorerTile[][] tiles) {
		this.tiles = tiles;
	}
	
}
