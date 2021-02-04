package GUI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import data.entity.Explorer;
import process.ExplorerManager;
import process.Simulation;

public class Dashboard extends JPanel {

	private static final long serialVersionUID = 1L;

	public static final int TILE_SIZE = 10;

	private Simulation simulation;

	public Dashboard(Simulation simulation) {
		this.simulation = simulation;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		printMap(g);
		printExplorers(g);
	}

	private void printMap(Graphics g) {
		for (int i = 0; i < 90; i++) {
			for (int j = 0; j < 90; j++) {
				switch (simulation.getEnvironment().getMap().getTile(i, j).getType()) {
				case "w":
					g.setColor(Color.BLUE);
					break;
				case "g":
					g.setColor(Color.LIGHT_GRAY);
					break;
				}
				g.fillRect(j * TILE_SIZE, i * TILE_SIZE, TILE_SIZE, TILE_SIZE);
			}
		}
	}

	private void printExplorers(Graphics g) {
		for (ExplorerManager explorerManager : simulation.getExplorerManagers()) {
			PaintVisitor paintVisitor = new PaintVisitor(g);
			Explorer explorer = explorerManager.getExplorer();
			explorer.accept(paintVisitor);
		}
	}

}
