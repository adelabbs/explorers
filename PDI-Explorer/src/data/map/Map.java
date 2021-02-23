package data.map;

/**
 * Contains the informations of the generated map. Only contains the tiles.
 * @author Léo
 *
 */
public class Map {

	public Tile[][] tiles = new Tile[90][90];
	
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
		
}
