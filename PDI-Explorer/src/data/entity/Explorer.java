package data.entity;

import data.map.ExplorerMap;

public class Explorer extends LivingEntity {

	private String name;
	private ExplorerMap map;
	private int communicationRange;
	
	public Explorer(String type, double[] position, double[] size, int maxHealth, int speed, int damage, 
			int scope, String name, ExplorerMap map, int communicationRange) {
		super(type, position, size, maxHealth, speed, damage, scope);
		this.name = name;
		this.map = map;
		this.communicationRange = communicationRange;
	}

	public String getName() {
		return name;
	}

	public ExplorerMap getMap() {
		return map;
	}

	public int getCommunicationRange() {
		return communicationRange;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMap(ExplorerMap map) {
		this.map = map;
	}

	public void setCommunicationRange(int communicationRange) {
		this.communicationRange = communicationRange;
	}
	
}
