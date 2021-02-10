package process.action;

import data.message.Message;
import process.communication.CommunicationSystem;
import process.manager.ExplorerManager;

public class SendMessageAction implements Action {
	private ExplorerManager explorerManager;
	private Message message;

	/**
	 * 
	 * @param explorerManager The message sender
	 * @param message         the message can be of any {@link Message} type
	 */
	public SendMessageAction(ExplorerManager explorerManager, Message message) {
		this.explorerManager = explorerManager;
		this.message = message;
	}

	public ExplorerManager getExplorerManager() {
		return explorerManager;
	}

	public Message getMessage() {
		return message;
	}

	/**
	 * Invokes the concrete sendMessage() implementation of the
	 * {@link CommunicationSystem} used by the {@link ExplorerManager}
	 */
	@Override
	public void execute() {
		CommunicationSystem communicationSystem = explorerManager.getCommunicationSystem();
		communicationSystem.sendMessage(message, explorerManager);
		//System.out.println(explorerManager.getExplorerName() + " just chatted");
	}
}
