package tests.geoffroy;

import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.JFrame;

import data.simulation.SimulationEntry;
import process.Simulation;
import process.SimulationUtility;

public class TestGUI extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;

	private static CardLayout card = new CardLayout(10, 10);
	private static Container c;

	private static final int EXPLORER_AMOUNT = 3;
	private static final int ANIMAL_AMOUNT = 0;
	private static final int CHEST_AMOUNT = 3;
	private static final int EXPLORATION_STRATEGY = 5;

	private SimulationEntry simulationEntry;
	private Simulation simulation;
	private Dashboard dashboard;

	public TestGUI() {
		super("Test movement");
		simulationEntry = new SimulationEntry(EXPLORER_AMOUNT, ANIMAL_AMOUNT, CHEST_AMOUNT, EXPLORATION_STRATEGY);
		simulation = new Simulation(simulationEntry);
		dashboard = new Dashboard();
		initLayout();
	}

	private void initLayout() {
		c = getContentPane();
		c.setLayout(card);

		c.add(dashboard);

		this.setSize(1920, 1080);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void run() {
		simulation.launch();
		while (simulation.isRunning()) {
			SimulationUtility.unitTime();
			simulation.update();
			dashboard.repaint();
		}
	}

}