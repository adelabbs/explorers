package environmentcreation;

import java.util.ArrayList;

import data.simulation.*;

public class EnvironmentCreator {

	public static Environment creation(int explorerAmount, int animalAmount, int chestAmount) {
		
		Environment environment = new Environment();
		environment.setMap(MapCreator.creation());
		environment.setExplorers(ExplorersCreator.creation(explorerAmount));
		environment.setExplorerAmount(explorerAmount);
		environment.setExplorerInit(explorerAmount);
		environment.setAnimals(AnimalsCreator.creation(animalAmount));
		environment.setChests(ChestsCreator.creation(chestAmount));
		environment.setChestAmount(chestAmount);
		environment.setFoundChest(0);
		environment.setItems(new ArrayList<Item>());
		environment.setObstacles(ObstaclesCreator.creation());
		environment.setState(1);
		return environment;
		
	}
	
}
