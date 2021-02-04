package tests.manual;

import data.simulation.SimulationEntry;
import process.Simulation;
import process.SimulationUtility;

public class TestThread extends Thread {
	private static final int EXPLORER_AMOUNT = 3;
	private static final int ANIMAL_AMOUNT = 0;
	private static final int CHEST_AMOUNT = 0;
	private static final int EXPLORATION_STRATEGY = 4;
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
			SimulationUtility.unitTime();
			simulation.update();
		}
	}
}