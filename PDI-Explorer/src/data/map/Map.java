package data.map;

/**
 * Contains the informations of the generated map. Only contains the tiles.
 *
 */
public class Map {

	public Tile[][] tiles = new Tile[90][90];
	
	/**
	 * Constructor.
	 * 
	 * @param tiles
	 */
	public Map(Tile[][] tiles) {
		this.tiles = tiles;
	}

	public Tile getTile(int i, int j) {
		return tiles[i][j];
	}
	
	public Tile[][] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}
	
	public String toString() {
		String res = "";
		for(int i = 0; i < 90; i ++) {
			for(int j = 0; j < 90; j ++) {
				res += tiles[i][j].getType();
			}
			res += "\n";
		}
		return res;
	}
	
}
