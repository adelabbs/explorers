package tests.geoffroy;

public class TestSimulation {
	public static void main(String[] args) {
		TestGUI simulationGUI = new TestGUI();
		Thread thread = new Thread(simulationGUI);
		thread.start();
	}
}