package data.map;

/**
 * Minimal element of the map. Contains the type of the tile, it can be g for ground or w for water.
 * @author Léo
 *
 */
public class Tile {

	private String type;
	
	public Tile(String type) {
		this.type = type;
	}
	
	public Tile() {}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
