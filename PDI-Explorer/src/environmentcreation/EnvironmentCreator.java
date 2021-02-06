package environmentcreation;

import java.util.ArrayList;

import data.simulation.*;
import environmentcreation.event.EntityCreationException;
import environmentcreation.mapcreation.MapCreator;

/**
 * 
 * Class used to create the Environment instance, calls all the needed methods to create it.
 * 
 */

public class EnvironmentCreator {

	public static void creation(int explorerAmount, int animalAmount, int chestAmount) throws EntityCreationException {
	
		Environment.getInstance().setMap(MapCreator.creation());
		Environment.getInstance().setEntities(EntityCreator.creation(explorerAmount, animalAmount));
		Environment.getInstance().setObstacles(ObstaclesCreator.creation(chestAmount));
		Environment.getInstance().setExplorerAmount(explorerAmount);
		Environment.getInstance().setExplorerInit(explorerAmount);
		Environment.getInstance().setChestAmount(chestAmount);
		Environment.getInstance().setFoundChest(0);
		Environment.getInstance().setItems(new ArrayList<Item>());
		
	}
	
}