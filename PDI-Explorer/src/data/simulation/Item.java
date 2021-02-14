package data.simulation;

public class Item {

	private String name;
	private String type;
	private int boost;
	
	public Item(String name, String type, int boost) {
		this.name = name;
		this.type = type;
		this.boost = boost;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}
	
	public int getBoost() {
		return boost;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public void setBoost(int boost) {
		this.boost = boost;
	}
		
}
