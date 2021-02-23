package environmentcreation.mapcreation;

/**
 * Temporary data class used to generate the map.
 * @author Léo
 *
 */
public class SubMap {

	private int top;
	private int right;
	private int bottom;
	private int left;
	private char[][] zones = new char[3][3];
	
	public SubMap(int top, int right, int bottom, int left) {
		this.top = top;
		this.right = right;
		this.bottom = bottom;
		this.left = left;
	}
	
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public int getRight() {
		return right;
	}
	public void setRight(int right) {
		this.right = right;
	}
	public int getBottom() {
		return bottom;
	}
	public void setBottom(int bottom) {
		this.bottom = bottom;
	}
	public int getLeft() {
		return left;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public char[][] getZones(){
		return zones;
	}
	public void setZones(char[][] zones) {
		this.zones = zones;
	}
	public String toString() {
		return top + "/" + right + "/" + bottom + "/" + left;
	}
	
}
