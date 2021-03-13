package process.visitor;

import data.message.HelpMessage;
import data.message.MapMessage;
import tests.manual.comms.TestMessage;

public interface MessageVisitor<T> {
	T visit(HelpMessage message);

	T visit(MapMessage message);

	T visit(TestMessage message);
}
