package data.simulation;

import java.util.HashMap;

import process.itemCreation.ItemFactory;

/**
 * This class is used to regroup the simulation entry parameters.
 *
 */
public class SimulationEntry {
	public static final int MIN_EXPLORER_AMOUNT = 3;
	public static final int MAX_EXPLORER_AMOUNT = 6;
	public static final int MIN_ANIMAL_AMOUNT = 0;
	public static final int MAX_ANIMAL_AMOUNT = 5;
	public static final int MIN_CHEST_AMOUNT = 1;
	public static final int MAX_CHEST_AMOUNT = 15;

	private int explorerAmount = 3;
	private int animalAmount = 0;
	private int chestAmount = 0;
	private int explorationStrategy = 0;
	// HashMap of Item, the key is the Item TYPE
	private static HashMap<String, Item> items = new HashMap<String, Item>();

	public SimulationEntry(int explorerAmount, int animalAmount, int chestAmount, int explorationStrategy) {
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

	public void setExplorationStrategy(int explorationStrategy) {
		this.explorationStrategy = explorationStrategy;
	}

	public static HashMap<String, Item> getItems() {
		return items;
	}

	public void add(String name) {
		Item item = ItemFactory.create(name);
		items.put(item.getType(), item);
	}

	public void remove(Item item) {
		items.remove(item.getType());
	}

	public void incrementExplorerAmount() {
		if (explorerAmount < MAX_EXPLORER_AMOUNT) {
			explorerAmount++;
		}
	}

	public void decrementExplorerAmount() {
		if (explorerAmount > MIN_EXPLORER_AMOUNT) {
			explorerAmount--;
		}
	}

	public void incrementAnimalAmount() {
		if (animalAmount < MAX_ANIMAL_AMOUNT) {
			animalAmount++;
		}
	}

	public void decrementAnimalAmount() {
		if (animalAmount > MIN_ANIMAL_AMOUNT) {
			animalAmount--;
		}
	}

	public void incrementChestAmount() {
		if (chestAmount < MAX_CHEST_AMOUNT) {
			chestAmount++;
		}
	}

	public void decrementChestAmount() {
		if (chestAmount > MIN_CHEST_AMOUNT) {
			chestAmount--;
		}
	}
}