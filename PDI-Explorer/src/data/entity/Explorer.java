package data.entity;

import data.map.ExplorerMap;
import data.map.ExplorerTile;
import process.visitor.EntityVisitor;

public class Explorer extends LivingEntity {

	private final static double EXPLORER_SIZE_X = 1.0;
	private final static double EXPLORER_SIZE_Y = 1.0;
	
	private final static int BASIC_MAX_HEALTH = 15;
	private final static int BASIC_SPEED = 8;
	private final static int BASIC_DAMAGE = 1;
	private final static int BASIC_SCOPE = 3;
	private final static int BASIC_COMMUNICATION_RANGE = 5;
	
	private String name;
	private ExplorerMap map;
	private int communicationRange;
	
	public Explorer(double[] position, String name) {
		super("Explorer", position, new double[]{EXPLORER_SIZE_X, EXPLORER_SIZE_Y},
				BASIC_MAX_HEALTH, BASIC_SPEED, BASIC_DAMAGE, BASIC_SCOPE);
		this.name = name;
		this.map = new ExplorerMap(new ExplorerTile[90][90]);
		this.communicationRange = BASIC_COMMUNICATION_RANGE;
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
