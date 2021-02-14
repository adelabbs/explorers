package tests.unit.action;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import data.entity.Chest;
import data.entity.Entity;
import data.entity.Explorer;
import data.map.ExplorerMap;
import data.map.ExplorerTile;
import data.simulation.Environment;
import process.action.CollectChestAction;

public class TestCollectChestAction {

	@Test
	public void shouldCollectCloseChest() {
		Environment e = Environment.getInstance();
		Chest chest = new Chest(new double[] { 2, 2 });
		Explorer explorer = new Explorer(new double[] { 2.0, 2.0 }, 15, 8, 3, 3, "Roger",
				new ExplorerMap(new ExplorerTile[90][90]), 5);
		ArrayList<Entity> chests = new ArrayList<Entity>();
		chests.add(chest);
		e.setObstacles(chests);
		e.setFoundChest(0);
		e.setChestAmount(1);
		CollectChestAction action = new CollectChestAction(chest, explorer);
		while (!action.isOver()) {
			action.execute();
		}
		assertEquals(1, e.getFoundChest());
		assertEquals(0, e.getObstacles().size());
	}

	@Test
	public void shouldNotCollectFarChest() {
		Environment e = Environment.getInstance();
		Chest chest = new Chest(new double[] { 1, 1 });
		Explorer explorer = new Explorer(new double[] { 2.0, 1.0 }, 15, 8, 3, 3, "Roger",
				new ExplorerMap(new ExplorerTile[90][90]), 5);
		ArrayList<Entity> chests = new ArrayList<Entity>();
		chests.add(chest);
		e.setObstacles(chests);
		e.setFoundChest(0);
		e.setChestAmount(1);
		CollectChestAction action = new CollectChestAction(chest, explorer);
		while (!action.isOver()) {
			action.execute();
		}
		assertEquals(0, e.getFoundChest());
		assertEquals(1, e.getObstacles().size());
	}

}
