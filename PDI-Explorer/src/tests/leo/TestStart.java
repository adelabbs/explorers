package tests.leo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tests.geoffroy.TestGUI;

public class TestStart implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		TestGUI simulationGUI = new TestGUI();
		Thread thread = new Thread(simulationGUI);
		thread.start();
	}
	
}
