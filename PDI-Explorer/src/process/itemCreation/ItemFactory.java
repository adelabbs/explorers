package process.itemCreation;

import data.simulation.Item;

public class ItemFactory {

	public static Item create(String name) {
		switch(name) {
		case "Knife" :
			return knifeCreation();
		case "Binoculars" :
			return binocularsCreation();
		case "Antenna" :
			return antennaCreation();
		default :
			return null;
		}
	}
	
	private static Item knifeCreation() {
		return new Item("Knife", "Damage", 1);
	}
	
	private static Item binocularsCreation() {
		return new Item("Binoculars", "Scope", 1);
	}
	
	private static Item antennaCreation() {
		return new Item("Antenna", "Communication Range", 1);
	}
	
}
