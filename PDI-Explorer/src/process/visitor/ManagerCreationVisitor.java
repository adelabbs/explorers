package process.visitor;

import data.entity.Bear;
import data.entity.Chest;
import data.entity.Explorer;
import data.entity.Obstacle;
import data.simulation.SimulationEntry;
import process.Simulation;
import process.factory.ManagerFactory;
import process.manager.LivingEntityManager;

/**
 * This visitor will return the corresponding {@link LivingEntityManager} to a given {@link LivingEntity}.
 *
 */
public class ManagerCreationVisitor implements EntityVisitor<LivingEntityManager> {

	private Simulation simulation;
	private SimulationEntry simulationEntry;

	public ManagerCreationVisitor(Simulation simulation, SimulationEntry simulationEntry) {
		this.simulation = simulation;
		this.simulationEntry = simulationEntry;
	}
	
	/**
	 * {@link Chest} are not controlled by a specific Manager.
	 */
	@Override
	public LivingEntityManager visit(Chest entity) throws IllegalArgumentException {
		throw new IllegalArgumentException("Not a LivingEntity");
	}
	
	/**
	 * {@link Obstacle} are not not controlled by a specific Manager.
	 */
	@Override
	public LivingEntityManager visit(Obstacle entity) throws IllegalArgumentException {
		throw new IllegalArgumentException("Not a LivingEntity");
	}

	@Override
	public LivingEntityManager visit(Explorer entity) {
		int strategy = simulationEntry.getExplorationStrategy();
		return ManagerFactory.createExplorerManager(simulation, entity, strategy);
	}

	@Override
	public LivingEntityManager visit(Bear entity) {
		return ManagerFactory.createBearManager(simulation, entity);
	}

}