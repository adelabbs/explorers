package tests.manual;

import data.simulation.SimulationEntry;
import environmentcreation.ExplorationStrategy;
import process.Simulation;

public class TestThread extends Thread {
	private static final int EXPLORER_AMOUNT = 3;
	private static final int ANIMAL_AMOUNT = 0;
	private static final int CHEST_AMOUNT = 0;
	private static final ExplorationStrategy EXPLORATION_STRATEGY = ExplorationStrategy.COMBAT;
	Simulation simulation;

	public TestThread() {
		SimulationEntry simulationEntry = new SimulationEntry(EXPLORER_AMOUNT, ANIMAL_AMOUNT, CHEST_AMOUNT,
				EXPLORATION_STRATEGY);
		simulation = new Simulation(simulationEntry);
	}

	@Override
	public void run() {
		if (simulation.isReady())
			simulation.launch();

		while (simulation.isRunning()) {
			simulation.update();
		}
	}
}