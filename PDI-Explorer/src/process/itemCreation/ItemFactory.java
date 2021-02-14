package process.itemCreation;

import data.simulation.Item;

public class ItemFactory {

	public static Item create(String name) {
		switch(name) {
		//Health items
		case "Helmet" :
			return helmetCreation();
		case "Chestplate" :
			return chestplateCreation();
			
		//Speed items
		case "Boots" :
			return bootsCreation();
		case "Trecking_shoes" :
			return treckingShoesCreation();
		
		//Damage items
		case "Knife" :
			return knifeCreation();
		case "Machete" :
			return macheteCreation();
			
		//Vision items
		case "Glasses" :
			return glassesCreation();
		case "Binoculars" :
			return binocularsCreation();
			
		//Communication range items
		case "Antenna" :
			return antennaCreation();
		case "Radio" :
			return radioCreation();
			
		//make an exception
		default :
			return null;
		}
	}
	
	//Health items
	private static Item helmetCreation() {
		return new Item("Helmet", "Health", 5);
	}
	private static Item chestplateCreation() {
		return  new Item("Chestplate", "Health", 10);
	}
	
	//Speed items
	private static Item bootsCreation() {
		return new Item("Boots", "Speed", 1);
	}
	private static Item treckingShoesCreation() {
		return new Item("Trecking_Shoes", "Speed", 2);
	}
	
	//Damage items
	private static Item knifeCreation() {
		return new Item("Knife", "Damage", 1);
	}
	private static Item macheteCreation() {
		return new Item("Machete", "Damage", 3);
	}
	
	//Vision items
	private static Item glassesCreation() {
		return new Item("Glasses", "Scope", 1);
	}
	private static Item binocularsCreation() {
		return new Item("Binoculars", "Scope", 2);
	}
	
	//Communication range items
	private static Item antennaCreation() {
		return new Item("Antenna", "Communication Range", 1);
	}
	private static Item radioCreation() {
		return new Item("Radio", "Communication Range", 2);
	}
	
}
