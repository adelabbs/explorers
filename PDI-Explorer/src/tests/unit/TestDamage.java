package tests.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import org.junit.Test;

import data.entity.Bear;
import data.entity.Explorer;
import data.entity.LivingEntity;
import data.simulation.Environment;
import process.action.AnimalMoveAction;
import process.factory.ManagerFactory;
import process.manager.ExplorerManager;

public class TestDamage {
	
	@Test
	public void shouldTakeDamageX() {

		// Given
		Bear bear = new Bear(new double[] { 0, 0 });
		Explorer explorer = new Explorer(new double[] { 1.0, 0.0 }, "Roger");
		Environment e = Environment.getInstance();

		ArrayList<LivingEntity> entities = new ArrayList<LivingEntity>();
		entities.add(explorer);
		entities.add(bear);
		e.setEntities(entities);

		AnimalMoveAction action = new AnimalMoveAction(bear, e);

		int initialBearHealth = bear.getMaxHealth();
		int initialExplorerHealth = explorer.getMaxHealth();

		// When
		action.execute();

		// Them
		assertEquals(initialBearHealth - explorer.getDamage(), bear.getHealth());
		assertEquals(initialExplorerHealth - bear.getDamage(), explorer.getHealth());
	}

	@Test
	public void shouldTakeDamageY() {

		// Given
		Bear bear = new Bear(new double[] { 0, 0 });
		Explorer explorer = new Explorer(new double[] { 0.0, 1.0 }, "Roger");
		Environment e = Environment.getInstance();

		ArrayList<LivingEntity> entities = new ArrayList<LivingEntity>();
		entities.add(explorer);
		entities.add(bear);
		e.setEntities(entities);

		AnimalMoveAction action = new AnimalMoveAction(bear, e);

		int initialBearHealth = bear.getMaxHealth();
		int initialExplorerHealth = explorer.getMaxHealth();

		// When
		action.execute();

		// Them
		assertEquals(initialBearHealth - explorer.getDamage(), bear.getHealth());
		assertEquals(initialExplorerHealth - bear.getDamage(), explorer.getHealth());
	}

	@Test
	public void shouldNotTakeDamageX() {

		// Given
		Bear bear = new Bear(new double[] { 0, 0 });
		Explorer explorer = new Explorer(new double[] { 2.0, 0.0 }, "Roger");
		Environment e = Environment.getInstance();

		ArrayList<LivingEntity> entities = new ArrayList<LivingEntity>();
		entities.add(explorer);
		entities.add(bear);
		e.setEntities(entities);

		AnimalMoveAction action = new AnimalMoveAction(bear, e);

		int initialBearHealth = bear.getMaxHealth();
		int initialExplorerHealth = explorer.getMaxHealth();

		// When
		action.execute();

		// Them
		assertFalse(initialBearHealth - explorer.getDamage() == bear.getHealth());
		assertFalse(initialExplorerHealth - bear.getDamage() == explorer.getHealth());
	}

	@Test
	public void shouldNotTakeDamageY() {

		// Given
		Bear bear = new Bear(new double[] { 0, 0 });
		Explorer explorer = new Explorer(new double[] { 0.0, 2.0 }, "Roger");
		Environment e = Environment.getInstance();

		ArrayList<LivingEntity> entities = new ArrayList<LivingEntity>();
		entities.add(explorer);
		entities.add(bear);
		e.setEntities(entities);

		AnimalMoveAction action = new AnimalMoveAction(bear, e);

		int initialBearHealth = bear.getMaxHealth();
		int initialExplorerHealth = explorer.getMaxHealth();

		// When
		action.execute();

		// Them
		assertFalse(initialBearHealth - explorer.getDamage() == bear.getHealth());
		assertFalse(initialExplorerHealth - bear.getDamage() == explorer.getHealth());
	}

	@Test
	public void shouldBeRemoved() {
		// Given
		Bear bear = new Bear(new double[] { 0, 0 });
		Explorer explorer = new Explorer(new double[] { 0.0, 1.0 }, "Roger");
		Environment e = Environment.getInstance();
		ExplorerManager em = ManagerFactory.createExplorerManager(null, explorer, ManagerFactory.ALL_ROUNDER_STRATEGY);
		explorer.setHealth(0);
		
		ArrayList<LivingEntity> entities = new ArrayList<LivingEntity>();
		entities.add(explorer);
		entities.add(bear);
		e.setEntities(entities);
				
		// When
		em.setRunning(true);
		em.run();
		
		
		// Them
		assertFalse(em.isRunning());
		assertFalse(e.getEntities().contains(explorer));
	}
	
	@Test
	public void shouldBeRemovedIfInfZero() {
		// Given
		Bear bear = new Bear(new double[] { 0, 0 });
		Explorer explorer = new Explorer(new double[] { 0.0, 1.0 }, "Roger");
		Environment e = Environment.getInstance();
		ExplorerManager em = ManagerFactory.createExplorerManager(null, explorer, ManagerFactory.ALL_ROUNDER_STRATEGY);
		explorer.setHealth(-50);
		
		ArrayList<LivingEntity> entities = new ArrayList<LivingEntity>();
		entities.add(explorer);
		entities.add(bear);
		e.setEntities(entities);
				
		// When
		em.setRunning(true);
		em.run();
		
		// Them
		assertFalse(em.isRunning());
		assertFalse(e.getEntities().contains(explorer));
	}
}
