package tests.manual;

import data.simulation.SimulationEntry;
import process.Simulation;

public class TestSimulation {
	private static final int EXPLORER_COUNT = 2;

	public static void main(String[] args) {
		SimulationEntry simulationEntry = new SimulationEntry(EXPLORER_COUNT);
		Simulation simulation = new Simulation(simulationEntry);
		simulation.simulate();
	}

}