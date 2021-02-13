package tests.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import tests.unit.action.TestCollectChestAction;
import tests.unit.action.TestCommunication;

@RunWith(Suite.class)
@Suite.SuiteClasses({TestCommunication.class, TestCollectChestAction.class})
public class SimulationTestSuite {

}
