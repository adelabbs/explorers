package process;

import java.util.HashMap;

import data.simulation.Item;
import data.simulation.SimulationEntry;

/**
 * This class will be used to configure all the simulation entry parameters.
 *
 */
public class Configuration {

	public static final int GLOBAL_ENVELOPPE = 1000;

	private HashMap<String, Item> upgrades = new HashMap<String, Item>();

	private SimulationEntry simulationEntry;
	private int enveloppe = GLOBAL_ENVELOPPE;

	public Configuration() {
		ConfigurationBuilder builder = new ConfigurationBuilder(this);
		builder.buildConfiguration();
	}

	public SimulationEntry getSimulationEntry() {
		return simulationEntry;
	}

	public int getEnveloppe() {
		return enveloppe;
	}

	/**
	 * 
	 * @param item
	 */
	public void add(Item item) {
		if (item != null) {
			upgrades.put(item.getId(), item);
		}
	}

	public HashMap<String, Item> getUpgrades() {
		return upgrades;
	}

}
