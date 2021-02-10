package process.communication;

import java.util.ArrayList;

import data.entity.Explorer;
import data.message.Message;
import process.SimulationUtility;
import process.manager.ExplorerManager;

/**
 * The radio is a naive implementation of the explorer's communication system.
 * Each message will be sent to every explorer within the sender's transmission
 * range.
 * 
 */
public class Radio implements CommunicationSystem {
	private ArrayList<ExplorerManager> explorerManagers = new ArrayList<ExplorerManager>();
	private volatile Message message = null;
	private ExplorerManager currentSender = null;

	@Override
	public void addExplorerManager(ExplorerManager explorerManager) {
		explorerManagers.add(explorerManager);
	}

	@Override
	public synchronized void sendMessage(Message message, ExplorerManager sender) {
		// The radio can only be used by 1 explorer at a time.
		if (currentSender != null) {
			try {
				// The explorer trying to send a message should wait until the radio is freed by
				// the current sender.
				wait();
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		} else {
			this.message = message;
			currentSender = sender;
			deliver();
			currentSender = null;
			notifyAll();
		}
	}

	private void deliver() {
		String currentSenderName = currentSender.getExplorerName();
		String receiverName = "";
		for (ExplorerManager receiver : explorerManagers) {
			receiverName = receiver.getExplorerName();
			// The current sender does not receive the messages
			if (!receiverName.equals(currentSenderName)
					&& isInRange(currentSender.getExplorer(), receiver.getExplorer())) {
				receiver.receive(message);
			}
		}
	}

	private boolean isInRange(Explorer sender, Explorer receiver) {
		int range = sender.getCommunicationRange();
		double[] senderPosition = sender.getPosition();
		double[] receiverPosition = receiver.getPosition();
		return SimulationUtility.distance(senderPosition, receiverPosition) <= range;
	}

	public void remove(ExplorerManager explorer) {
		explorerManagers.remove(explorer);
	}

	public Message getMessage() {
		return message;
	}

}
