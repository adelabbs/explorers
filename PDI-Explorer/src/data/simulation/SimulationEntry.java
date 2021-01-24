package data.simulation;

import java.util.ArrayList;

/**
 * This class is used to regroup the simulation entry parameters.
 *
 */
public class SimulationEntry {
	private int explorerCount;
	private ArrayList<Item> items = new ArrayList<Item>();

	// TODO Add the remaining simulation parameters

	public SimulationEntry(int explorerCount) {
		this.explorerCount = explorerCount;
	}

	public int getExplorerCount() {
		return explorerCount;
	}

	public void add(Item item) throws ItemAlreadyExistsException {
		if (items.contains(item)) {
			throw new ItemAlreadyExistsException(item);
		} else {
			items.add(item);
		}
	}
	
	public void remove(Item item) {
		items.remove(item);
	}
	
}