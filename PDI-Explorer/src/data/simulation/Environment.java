package data.simulation;

import java.util.ArrayList;

import data.entity.*;
import data.map.GeneralExplorerMap;
import data.map.Map;

/** 
 * Contains all the data needed for a simulation.
 * 
 * This class has to be instanced only once.
 * Consequently, this is a Singleton class.
 * 
 * @author Léo COQUET
 *
 */

public final class Environment {

	//The only instance of Environment
	private static volatile Environment instance = null;
	
	private Map map;
	private ArrayList<LivingEntity> entities = new ArrayList<LivingEntity>();
	private ArrayList<Entity> obstacles = new ArrayList<Entity>();
	private int explorerAmount;
	private int explorerInit;
	private int chestAmount;
	private int foundChest;
	private ArrayList<Item> items = new ArrayList<Item>();
	private GeneralExplorerMap generalExplorerMap = new GeneralExplorerMap();
	
	private Environment() {}
	
	public Map getMap() {
		return map;
	}
	
	public final static Environment getInstance() {
		if(Environment.instance == null )
			synchronized(Environment.class) {
				if(Environment.instance == null)
					Environment.instance = new Environment();
			}
		return Environment.instance;
	}

	public ArrayList<LivingEntity> getEntities(){
		return entities;
	}
	public ArrayList<Entity> getObstacles(){
		return obstacles;
	}
	public int getExplorerAmount() {
		return explorerAmount;
	}
	public int getExplorerInit() {
		return explorerInit;
	}
	public int getChestAmount() {
		return chestAmount;
	}
	public int getFoundChest() {
		return foundChest;
	}
	public ArrayList<Item> getItems() {
		return items;
	}
	public GeneralExplorerMap getGeneralExplorerMap() {
		return generalExplorerMap;
	}

	public void setMap(Map map) {
		this.map = map;
	}
	public void setEntities(ArrayList<LivingEntity> entities) {
		this.entities = entities;
	}
	public void setObstacles(ArrayList<Entity> obstacles) {
		this.obstacles = obstacles;
	}
	public void setExplorerAmount(int explorerAmount) {
		this.explorerAmount = explorerAmount;
	}
	public void setExplorerInit(int explorerInit) {
		this.explorerInit = explorerInit;
	}
	public void setChestAmount(int chestAmount) {
		this.chestAmount = chestAmount;
	}
	public void setFoundChest(int foundChest) {
		this.foundChest = foundChest;
	}
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	public void setGeneralExplorerMap(GeneralExplorerMap generalExplorerMap) {
		this.generalExplorerMap = generalExplorerMap;
	}
	
	public synchronized void decrementExplorerAmount() {
		if(explorerAmount > 0)
			explorerAmount --;
	}
	public synchronized void incrementFoundChest() {
		if(foundChest < chestAmount)
			foundChest++;
	}
	public synchronized void remove(LivingEntity entity) {
		entities.remove(entity);
	}
	public void add(Entity entity) {
		if(entity!= null) {
			obstacles.add(entity);
		}
	}
	public synchronized void remove(Entity entity) {
		if(obstacles.contains(entity)) {
			obstacles.remove(entity);
			incrementFoundChest();
		}
	}
	public void remove(Item item) {
		items.remove(item);
	}
}
