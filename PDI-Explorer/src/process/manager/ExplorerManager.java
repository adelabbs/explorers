package process.manager;

import java.util.ArrayList;
import java.util.LinkedList;

import data.entity.Explorer;
import data.message.Message;
import process.Simulation;
import process.SimulationUtility;
import process.action.Action;
import process.communication.CommunicationSystem;
import process.strategy.ExplorationStrategy;

/**
 * The explorer controller class. Each instance of the class represents an
 * explorer in the simulation environment.
 *
 */
public class ExplorerManager extends LivingEntityManager {
	private Simulation simulation;
	private Explorer explorer;
	private ExplorationStrategy strategy;
	private LinkedList<Action> actions = new LinkedList<Action>();
	private CommunicationSystem communicationSystem;
	private ArrayList<Message> messages = new ArrayList<Message>();

	public ExplorerManager(Simulation simulation, Explorer explorer) {
		this.simulation = simulation;
		this.explorer = explorer;
	}

	@Override
	public void run() {
		while (!isDead() && isRunning()) {
			SimulationUtility.unitTime();
			strategy.decide();
			Action action = actions.peekFirst();
			if (action != null) {
				action.execute();
				if (action.isOver()) {
					removeAction();
				}
			}
		}
	}

	public void receive(Message message) {
		messages.add(message);
		// System.out.println(getExplorerName() + " just received : '" + message + "'");
	}

	public void planAction(Action action) {
		actions.addLast(action);
	}

	private void removeAction() {
		actions.removeFirst();
	}

	public Action getCurrentAction() {
		return actions.peekFirst();
	}

	public Simulation getSimulation() {
		return simulation;
	}

	public Explorer getExplorer() {
		return explorer;
	}

	public String getExplorerName() {
		return explorer.getName();
	}

	public CommunicationSystem getCommunicationSystem() {
		return communicationSystem;
	}

	public void setCommunicationSystem(CommunicationSystem communicationSystem) {
		this.communicationSystem = communicationSystem;
	}

	public ArrayList<Message> getMessages() {
		return messages;
	}

	public ExplorationStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(ExplorationStrategy strategy) {
		this.strategy = strategy;
	}

	@Override
	public void updatePosition(double[] newPosition) {
		explorer.setPosition(newPosition);
	}

	@Override
	public void updateHealth(int newHealth) {
		explorer.setHealth(newHealth);
	}

}