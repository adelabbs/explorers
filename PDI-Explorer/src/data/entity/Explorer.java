package data.entity;

import data.map.ExplorerMap;
import data.map.ExplorerTile;
import data.simulation.Item;
import data.simulation.SimulationEntry;
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
				BASIC_MAX_HEALTH + boostVerifier("Health"),
				BASIC_SPEED + boostVerifier("Speed"),
				BASIC_DAMAGE + boostVerifier("Damage"),
				BASIC_SCOPE + boostVerifier("Scope"));
		this.name = name;
		this.map = new ExplorerMap(new ExplorerTile[90][90]);
		this.communicationRange = BASIC_COMMUNICATION_RANGE + boostVerifier("Communication Range");
	}
	
	private static int boostVerifier(String type) {
		for(Item item : SimulationEntry.getItems()) {
			if(item.getType().equals(type))
				return item.getBoost();
		}
		return 0;
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
