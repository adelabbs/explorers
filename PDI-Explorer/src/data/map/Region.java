package data.map;

public class Region {
	private int id;
	private Point topLeft;
	private Point bottomRight;

	public Region(int id, Point topLeft, Point bottomRight) {
		this.id = id;
		this.topLeft = topLeft;
		this.bottomRight = bottomRight;
	}

	public int getId() {
		return id;
	}

	public Point getTopLeft() {
		return topLeft;
	}

	public Point getBottomRight() {
		return bottomRight;
	}

}