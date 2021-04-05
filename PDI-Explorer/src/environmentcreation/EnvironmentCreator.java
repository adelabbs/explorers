package environmentcreation;

import java.util.ArrayList;

import data.simulation.*;
import environmentcreation.event.EntityCreationException;
import environmentcreation.mapcreation.MapCreator;


/**
 * Class calling every functions to create the environment.
 * 
 * @author Léo
 *
 */
public class EnvironmentCreator {
	
	/**
	 * Calls every functions to create environment.
	 * 
	 * @param explorerAmount The amount of explorers.
	 * @param animalAmount The amount of animals.
	 * @param chestAmountzer The amount of chests.
	 * @throws EntityCreationException
	 */
	public static void creation(int explorerAmount, int animalAmount, int chestAmountzer) throws EntityCreationException {

		int chestAmount = (int) (Math.random() * 5) + 10;
		
		Environment.getInstance().setMap(MapCreator.creation());
		Environment.getInstance().setEntities(EntityCreator.creation(explorerAmount, animalAmount));
		Environment.getInstance().setObstacles(ObstaclesCreator.creation(chestAmount));
		Environment.getInstance().setExplorerAmount(explorerAmount);
		Environment.getInstance().setExplorerInit(explorerAmount);
		Environment.getInstance().setChestAmount(chestAmount);
		Environment.getInstance().setFoundChest(0);
		Environment.getInstance().setItems(new ArrayList<Item>());
		Environment.getInstance().setGeneralExplorerMap(GeneralExplorerMapCreator.creation());
		
	}
	
}