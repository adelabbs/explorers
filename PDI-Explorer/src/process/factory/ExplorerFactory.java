package process.factory;

import data.entity.Explorer;
import data.simulation.Item;
import data.map.ExplorerMap;
import tests.manual.SimuPara;

/**
 * This utility class will allow the creation of the appropriate {@link Explorer} given the selected {@link Item}
 */
public class ExplorerFactory {
	public static Explorer createTestExplorer(ExplorerMap explorerMap) {
		return new Explorer(SimuPara.TEST_POSITION, SimuPara.TEST_EXPLORER_MAX_HEALTH, SimuPara.TEST_EXPLORER_SPEED, SimuPara.TEST_EXPLORER_DAMAGE,
				SimuPara.TEST_EXPLORER_SCOPE, SimuPara.TEST_EXPLORER_NAME, explorerMap,
				SimuPara.TEST_COMMUNICATION_RANGE);
	}

	public static Explorer createExplorer() {
		// TODO
		return null;
	}
}