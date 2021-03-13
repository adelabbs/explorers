package process.action;

import data.entity.Explorer;
import data.message.Message;

@SuppressWarnings("rawtypes")
public class SendMessageAction implements Action {

	Message message;
	Explorer explorer;
	
	public SendMessageAction(Message message, Explorer explorer) {
		this.message = message;
		this.explorer = explorer;
	}
	
	@Override
	public void execute() {
		message.send();
	}

	@Override
	public boolean isOver() {
		return true;
	}
	
}
