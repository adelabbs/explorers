package data.entity;

import process.visitor.EntityVisitor;

public abstract class Entity {

	private String type;
	private double position[] = new double[2];
	private double size[] = new double[2];
	
	public Entity(String type, double[] position, double[] size) {
		this.type = type;
		this.position = position;
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public double[] getPosition() {
		return position;
	}

	public double[] getSize() {
		return size;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPosition(double[] position) {
		this.position = position;
	}

	public void setSize(double[] size) {
		this.size = size;
	}	
	
	public abstract<T> T accept(EntityVisitor<T> visitor);
}
