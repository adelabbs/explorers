package process.visitor;

import data.message.DangerMessage;
import data.message.MapMessage;
import tests.manual.comms.TestMessage;

public interface MessageVisitor<T> {
	T visit(DangerMessage message);

	T visit(MapMessage message);

	T visit(TestMessage message);
}
