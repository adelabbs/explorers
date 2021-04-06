package process;

import tests.manual.SimuPara;

/**
 * 
 * Utility class for Simulation
 *
 */
public class SimulationUtility {

	/**
	 * Simulates the unit time (for an iteration) defined for the simulation.
	 */
	public static void unitTime() {
		try {
			Thread.sleep(SimuPara.SIMULATION_SPEED);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * 
	 * @param positionA
	 * @param positionB
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static double distance(double[] positionA, double[] positionB) throws IllegalArgumentException {
		if (positionA.length >= 2 && positionB.length >= 2)
			return Math.sqrt((Math.pow(positionA[0] - positionB[0], 2) + (Math.pow(positionA[1] - positionB[1], 2))));
		else
			throw new IllegalArgumentException();
	}
}