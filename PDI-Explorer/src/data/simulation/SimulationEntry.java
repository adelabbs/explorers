package data.simulation;

import java.util.ArrayList;

import environmentcreation.ExplorationStrategy;

/**
 * This class is used to regroup the simulation entry parameters.
 *
 */
public class SimulationEntry {
	private int explorerAmount;
	private int animalAmount;
	private int chestAmount;
	private ExplorationStrategy explorationStrategy;
	private ArrayList<Item> items = new ArrayList<Item>();

	public SimulationEntry(int explorerAmount, int animalAmount, int chestAmount,
			ExplorationStrategy explorationStrategy) {
		this.explorerAmount = explorerAmount;
		this.animalAmount = animalAmount;
		this.chestAmount = chestAmount;
		this.explorationStrategy = explorationStrategy;
	}

	public int getExplorerAmount() {
		return explorerAmount;
	}

	public int getAnimalAmount() {
		return animalAmount;
	}

	public int getChestAmount() {
		return chestAmount;
	}

	public ExplorationStrategy getExplorationStrategy() {
		return explorationStrategy;
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