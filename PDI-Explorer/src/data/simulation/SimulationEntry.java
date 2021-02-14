package data.simulation;

import java.util.ArrayList;

/**
 * This class is used to regroup the simulation entry parameters.
 *
 */
public class SimulationEntry {
	private int explorerAmount;
	private int animalAmount;
	private int chestAmount;
	private int explorationStrategy;
	private static ArrayList<Item> items = new ArrayList<Item>();

	public SimulationEntry(int explorerAmount, int animalAmount, int chestAmount,
			int explorationStrategy) {
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

	public int getExplorationStrategy() {
		return explorationStrategy;
	}

	public static ArrayList<Item> getItems(){
		return items;
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