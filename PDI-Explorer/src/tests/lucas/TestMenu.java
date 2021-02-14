package tests.lucas;

import java.awt.Container;
import java.io.IOException;

import javax.swing.JFrame;

import environmentcreation.event.EntityCreationException;

@SuppressWarnings({ "serial", "unused" })
public class TestMenu extends JFrame  {
	private Menu2 menu;
	private static Container c;
	public TestMenu (Menu2 menu) throws IOException {
		super();
		this.menu = menu;
		c = this.getContentPane();
		c.add(menu);
		this.setSize(940, 940);
	    this.setVisible(true);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}
	public static void main(String[] args) throws IOException {
			Menu2 menu = new Menu2();
			TestMenu test = new TestMenu(menu);
			//System.out.println(String.valueOf(menu.getSim().getItems()) + menu.getSim().getExplorerAmount() + menu.getSim().getAnimalAmount());
	}
	
}
