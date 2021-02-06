package data.entity;

import data.map.ExplorerMap;
import process.visitor.EntityVisitor;

public class Explorer extends LivingEntity {

	private final static double EXPLORER_SIZE_X = 1.0;
	private final static double EXPLORER_SIZE_Y = 2.0;
	
	private String name;
	private ExplorerMap map;
	private int communicationRange;
	
	public Explorer(double[] position, int maxHealth, int speed, int damage, 
			int scope, String name, ExplorerMap map, int communicationRange) {
		super("Explorer", position, new double[]{EXPLORER_SIZE_X, EXPLORER_SIZE_Y}, maxHealth, speed, damage, scope);
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

	@Override
	public <T> T accept(EntityVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
}
