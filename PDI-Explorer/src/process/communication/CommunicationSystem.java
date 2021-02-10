package process.communication;

import data.message.Message;
import process.manager.ExplorerManager;

/**
 * The abstract {@link CommunicationSystem} represents the communication system
 * the explorers use during the simulation. Only the add operation and the send
 * message operation are universal as the receive operation may differ between
 * different concrete communication systems.
 */
public interface CommunicationSystem {
	/**
	 * The add operation is necessary for the initialization of the simulation. The
	 * implementation is free to choose the appropriate data structure.
	 * 
	 * @param explorer
	 */
	void addExplorerManager(ExplorerManager explorerManager);

	/**
	 * The {@link Message} is sent to as many {@link ExplorerManager} as possible so
	 * the receivers do not need to be passed as parameters. If a communication
	 * system requires a specific receiver, it should implement sendMessage with
	 * another prototype.
	 * 
	 * @param message
	 */
	void sendMessage(Message message, ExplorerManager author);
}
