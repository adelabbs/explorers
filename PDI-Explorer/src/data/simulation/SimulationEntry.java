package data.simulation;

import java.util.HashMap;

import process.itemCreation.ItemFactory;

/**
 * This class is used to regroup the simulation entry parameters.
 *
 */
public class SimulationEntry {
	private int explorerAmount;
	private int animalAmount;
	private int chestAmount;
	private int explorationStrategy;
	//HashMap of Item, the key is the Item TYPE
	private static HashMap<String, Item> items = new HashMap<String, Item>();

	public SimulationEntry(int explorerAmount, int animalAmount, int chestAmount,
			int explorationStrategy) {
		this.explorerAmount = explorerAmount;
		this.animalAmount = animalAmount;
		this.chestAmount = chestAmount;
		this.explorationStrategy = explorationStrategy;
	}

	public void setExplorerAmount(int explorerAmount) {
		this.explorerAmount = explorerAmount;
	}

	public void setAnimalAmount(int animalAmount) {
		this.animalAmount = animalAmount;
	}

	public void setChestAmount(int chestAmount) {
		this.chestAmount = chestAmount;
	}

	public void setExplorationStrategy(int explorationStrategy) {
		this.explorationStrategy = explorationStrategy;
	}

	public static void setItems(HashMap<String, Item> items) {
		SimulationEntry.items = items;
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

	public static HashMap<String, Item> getItems(){
		return items;
	}
	
	public void add(String name) {
		Item item = ItemFactory.create(name);
		if (items.containsKey(item.getType()))
			items.remove(item.getType());
		items.put(item.getType(), item);
	}

	public void remove(Item item) {
		items.remove(item.getType());
	}
}