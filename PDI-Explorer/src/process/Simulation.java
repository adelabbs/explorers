package process;

import java.util.ArrayList;
import java.util.HashMap;

import data.simulation.SimulationEntry;
import environmentcreation.EnvironmentCreator;
import environmentcreation.RegionsCreator;
import environmentcreation.event.EntityCreationException;
import process.factory.ManagerFactory;
import process.manager.ExplorerManager;
import process.manager.LivingEntityManager;
import process.visitor.ManagerCreationVisitor;
import data.entity.LivingEntity;
import data.map.Region;
import data.simulation.Environment;

/**
 * The Simulation processing class. It contains and prepares all
 * {@link ExplorerManager}.
 * 
 * @author Adel
 *
 */
public class Simulation {
	private SimulationEntry simulationEntry;
	private Environment environment = Environment.getInstance();
	private SimulationState state;

	private ArrayList<LivingEntityManager> managers = new ArrayList<LivingEntityManager>();

	private volatile HashMap<Integer, RegionManager> regionManagers;

	/**
	 * 
	 * @param simulationEntry the simulation entry parameters
	 */
	public Simulation(SimulationEntry simulationEntry) {
		this.simulationEntry = simulationEntry;
		buildSimulation();
	}

	private void buildSimulation() {
		int explorerAmount = simulationEntry.getExplorerAmount();
		int animalAmount = simulationEntry.getAnimalAmount();
		int chestAmount = simulationEntry.getChestAmount();
		try {
			EnvironmentCreator.creation(explorerAmount, animalAmount, chestAmount);
			if (ManagerFactory.REGION_STRATEGY == simulationEntry.getExplorationStrategy()) {
				buildRegions();
				buildRegionManagers();
			}
			buildManagers();
			setState(SimulationState.READY);
		} catch (EntityCreationException e) {
			e.printStackTrace();
		}
	}

	private void buildManagers() {
		Environment e = Environment.getInstance();
		for (LivingEntity livingEntity : e.getEntities()) {
			ManagerCreationVisitor visitor = new ManagerCreationVisitor(this, simulationEntry);
			LivingEntityManager livingEntityManager = livingEntity.accept(visitor);
			managers.add(livingEntityManager);
		}
	}

	private void buildRegionManagers() {
		Environment e = Environment.getInstance();
		regionManagers = new HashMap<Integer, RegionManager>();
		for (Region region : e.getRegions().values()) {
			RegionManager regionManager = new RegionManager(region);
			regionManagers.put(regionManager.getRegion().getId(), regionManager);
		}
	}

	private void buildRegions() {
		Environment e = Environment.getInstance();
		e.setRegions(RegionsCreator.creation());
	}

	public void launch() {
		startAllManagerThreads();
		setState(SimulationState.RUNNING);
	}

	public void update() {
		if (environment.getExplorerAmount() <= 0 || (environment.getFoundChest() >= environment.getChestAmount())) {
			setState(SimulationState.OVER);
			stopAllManagerThreads();
		}
	}

	public void startAllManagerThreads() {
		for (LivingEntityManager manager : managers) {
			manager.setRunning(true);
			manager.start();
		}
	}

	public void stopAllManagerThreads() {
		for (LivingEntityManager manager : managers) {
			manager.setRunning(false);
		}
	}

	public void add(LivingEntityManager livingEntityManager) {
		if (!managers.contains(livingEntityManager)) {
			managers.add(livingEntityManager);
		}
	}

	public void remove(LivingEntityManager livingEntityManager) {
		managers.remove(livingEntityManager);
	}

	public ArrayList<LivingEntityManager> getManagers() {
		return managers;
	}

	public HashMap<Integer, RegionManager> getRegionManagers() {
		return regionManagers;
	}

	public SimulationState getState() {
		return state;
	}

	public void setState(SimulationState state) {
		this.state = state;
	}

	public boolean isRunning() {
		return state.equals(SimulationState.RUNNING);
	}

	public boolean isReady() {
		return state.equals(SimulationState.READY);
	}

	public boolean isPaused() {
		return state.equals(SimulationState.PAUSED);
	}

	public boolean isOver() {
		return state.equals(SimulationState.OVER);
	}
}