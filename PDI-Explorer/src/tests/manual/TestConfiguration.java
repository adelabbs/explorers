package tests.manual;

import data.simulation.Item;
import process.Configuration;

/**
 * 
 * @author Adel
 *
 */
public class TestConfiguration {

	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		for (Item item : configuration.getUpgrades().values()) {
			System.out.println(item);
		}
	}

}
