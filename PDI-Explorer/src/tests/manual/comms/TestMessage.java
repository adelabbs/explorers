package tests.manual.comms;

import data.message.Message;
import process.visitor.MessageVisitor;

public class TestMessage extends Message {

	public TestMessage(String message) {
		super(message);
	}

	@Override
	public <T> T accept(MessageVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
