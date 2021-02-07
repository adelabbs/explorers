package process.communication;

import java.util.ArrayList;

import data.message.Message;
import process.manager.ExplorerManager;

/**
 * The radio is a naive implementation of the explorer's communication system. Each
 * message will be sent to every explorer within the sender's transmission
 * range.
 * 
 * @author dedely
 *
 */
public class Radio implements CommunicationSystem {
	private ArrayList<ExplorerManager> explorerManagers = new ArrayList<ExplorerManager>();

	@Override
	public void addExplorer(ExplorerManager explorer) {
		if (!explorerManagers.contains(explorer)) {
			explorerManagers.add(explorer);
		}
	}

	public void remove(ExplorerManager explorer) {
		explorerManagers.remove(explorer);
	}

	@Override
	public void sendMessage(Message message) {
		
	}

}
