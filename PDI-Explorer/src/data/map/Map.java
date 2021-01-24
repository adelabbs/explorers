package data.map;

public class Map {

	public Tile[][] tiles = new Tile[90][90];
	
	public Map(Tile[][] tiles) {
		this.tiles = tiles;
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}
		
}
