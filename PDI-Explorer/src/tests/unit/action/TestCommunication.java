package tests.unit.action;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import data.entity.Explorer;
import data.map.ExplorerMap;
import data.map.ExplorerTile;
import data.message.Message;
import process.SimulationUtility;
import process.action.Action;
import process.action.SendMessageAction;
import process.communication.CommunicationSystem;
import process.communication.Radio;
import process.manager.ExplorerManager;
import tests.manual.comms.JustChattingStrategy;
import tests.manual.comms.TestMessage;

/**
 * Unit tests for the communication between {@link ExplorerManager} using the
 * {@link Radio} system
 * 
 * @author dedely
 *
 */
public class TestCommunication {
	private ArrayList<ExplorerManager> explorerManagers = new ArrayList<ExplorerManager>();
	private CommunicationSystem communicationSystem = new Radio();
	private static final String TEST_MESSAGE = "Hello, World!";

	@Before
	public void prepareManagersAndCommunicationSystem() {
		ArrayList<Explorer> explorers = new ArrayList<>();
		explorers.add(new Explorer(new double[] { 5.0, 6.0 }, 15, 8, 3, 3, "Roger",
				new ExplorerMap(new ExplorerTile[90][90]), 5));
		explorers.add(new Explorer(new double[] { 7.0, 5.0 }, 15, 8, 3, 3, "Michel",
				new ExplorerMap(new ExplorerTile[90][90]), 5));
		explorers.add(new Explorer(new double[] { 3.0, 2.0 }, 15, 8, 3, 3, "Didier",
				new ExplorerMap(new ExplorerTile[90][90]), 5));

		for (Explorer explorer : explorers) {
			ExplorerManager chattingExplorerManager = new ExplorerManager(null, explorer);
			chattingExplorerManager.setStrategy(new JustChattingStrategy(chattingExplorerManager));
			communicationSystem.addExplorerManager(chattingExplorerManager);
			chattingExplorerManager.setCommunicationSystem(communicationSystem);
			explorerManagers.add(chattingExplorerManager);
		}
	}

	/**
	 * This tests if a message can be sent from 1 explorer to all the potential
	 * receivers without worrying about the preconditions We only test the
	 * reception.
	 */
	@Test
	public void testMessageReception() {
		Message message = new TestMessage(TEST_MESSAGE);
		ExplorerManager sender = explorerManagers.get(0);
		Action action = new SendMessageAction(sender, message);
		action.execute();

		ExplorerManager receiver1 = explorerManagers.get(1);
		ExplorerManager receiver2 = explorerManagers.get(2);

		assertEquals(0, sender.getMessages().size());
		assertEquals(1, receiver1.getMessages().size());
		assertEquals(1, receiver2.getMessages().size());

		assertTrue(receiver1.getMessages().get(0).getMessage().equals(TEST_MESSAGE));
		assertTrue(receiver2.getMessages().get(0).getMessage().equals(TEST_MESSAGE));
	}
	
	@Test
	public void testReceptionWithinSenderRange() {
		Message message = new TestMessage(TEST_MESSAGE);
		ExplorerManager sender = explorerManagers.get(0);
		Action action = new SendMessageAction(sender, message);
		action.execute();

		ExplorerManager receiver1 = explorerManagers.get(1);
		ExplorerManager receiver2 = explorerManagers.get(2);

		int range = sender.getExplorer().getCommunicationRange();
		double distance1 = SimulationUtility.distance(receiver1.getExplorer().getPosition(),
				sender.getExplorer().getPosition());
		double distance2 = SimulationUtility.distance(receiver2.getExplorer().getPosition(),
				sender.getExplorer().getPosition());
		
		assertEquals(0, sender.getMessages().size());

		if (distance1 <= range) {
			assertEquals(1, receiver1.getMessages().size());
			assertTrue(receiver1.getMessages().get(0).getMessage().equals(TEST_MESSAGE));
		} else {
			assertEquals(0, receiver1.getMessages().size());
		}

		if (distance2 <= range) {
			assertEquals(1, receiver2.getMessages().size());
			assertTrue(receiver2.getMessages().get(0).getMessage().equals(TEST_MESSAGE));
		} else {
			assertEquals(0, receiver2.getMessages().size());
		}
	}

}
