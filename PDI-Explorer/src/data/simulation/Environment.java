package data.simulation;

import java.util.ArrayList;

import data.entity.*;
import data.map.Map;

/**
 * 
 * Contains all the data needed for a simulation.
 * 
 * Environment has four states :
 * 0. Finished
 * 1. Running
 * 2. Paused
 * 3. Stopped
 * 
 * @author Léo COQUET
 *
 */

public class Environment {

	private Map map;
	private ArrayList<Explorer> explorers;
	private int explorerAmount;
	private int explorerInit;
	private ArrayList<LivingEntity> animals;
	private ArrayList<Chest> chests;
	private int chestAmount;
	private int foundChest;
	private ArrayList<Item> items;
	private ArrayList<Entity> obstacles;
	private ArrayList<BreakableEntity> breakableObstacles;
	private int state;
	
	public Map getMap() {
		return map;
	}
	public ArrayList<Explorer> getExplorers() {
		return explorers;
	}
	public int getExplorerAmount() {
		return explorerAmount;
	}
	public int getExplorerInit() {
		return explorerInit;
	}
	public ArrayList<LivingEntity> getAnimals() {
		return animals;
	}
	public ArrayList<Chest> getChests() {
		return chests;
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
	public ArrayList<Entity> getObstacles() {
		return obstacles;
	}
	public ArrayList<BreakableEntity> getBreakableObstacles() {
		return breakableObstacles;
	}
	public int getState() {
		return state;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public void setExplorers(ArrayList<Explorer> explorers) {
		this.explorers = explorers;
	}
	public void setExplorerAmount(int explorerAmount) {
		this.explorerAmount = explorerAmount;
	}
	public void setExplorerInit(int explorerInit) {
		this.explorerInit = explorerInit;
	}
	public void setAnimals(ArrayList<LivingEntity> animals) {
		this.animals = animals;
	}
	public void setChests(ArrayList<Chest> chests) {
		this.chests = chests;
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
	public void setObstacles(ArrayList<Entity> obstacles) {
		this.obstacles = obstacles;
	}
	public void setBreakableObstacles(ArrayList<BreakableEntity> breakableObstacles) {
		this.breakableObstacles = breakableObstacles;
	}
	public void setState(int state) {
		this.state = state;
	}
		
}
