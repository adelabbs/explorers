package data.message;

import data.map.ExplorerMap;
import process.visitor.MessageVisitor;

public class MapMessage extends Message {
	private ExplorerMap map;

	public MapMessage(String message, ExplorerMap map) {
		super(message);
		this.map = map;
	}

	public ExplorerMap getMap() {
		return map;
	}

	public void setMap(ExplorerMap map) {
		this.map = map;
	}

	@Override
	public <T> T accept(MessageVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
