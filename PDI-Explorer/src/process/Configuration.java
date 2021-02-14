package process;

import data.simulation.SimulationEntry;

/**
 * This class is used to configure all the simulation entry parameters.
 *
 */
public class Configuration {

	public static final int GLOBAL_ENVELOPPE = 1000;

	private SimulationEntry simulationEntry;
	private int enveloppe = GLOBAL_ENVELOPPE;

	public SimulationEntry getSimulationEntry() {
		return simulationEntry;
	}

	public int getEnveloppe() {
		return enveloppe;
	}
}
