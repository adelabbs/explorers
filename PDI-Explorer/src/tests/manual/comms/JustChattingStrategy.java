package tests.manual.comms;

import process.action.SendMessageAction;
import process.manager.ExplorerManager;
import process.strategy.ExplorationStrategy;

public class JustChattingStrategy extends ExplorationStrategy {

	public JustChattingStrategy(ExplorerManager explorerManager) {
		super(explorerManager);
	}

	@Override
	public void decide() {
		ExplorerManager explorerManager = getExplorerManager();
		TestMessage message = new TestMessage("Hi, my name is " + explorerManager.getExplorerName());
		SendMessageAction action = new SendMessageAction(explorerManager, message);
		explorerManager.planAction(action);
	}

}
