package gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * 
 * @author lespi
 * 
 * This class displays the global map on the HUD.
 *
 */
public class MapFX extends Canvas {

	protected static double TILE_SIZE = 10;
	protected static double width;
	protected static double height;
	private MapPainterFX mp;
	
	/**
	 * 
	 * @param arg1
	 * @param arg2
	 */
	@SuppressWarnings("static-access")
	public MapFX(double arg1, double arg2) {
		super(arg1, arg2);
		this.width = arg1;
		this.height = arg2;
		TILE_SIZE = (arg2 - arg2/20)/90;
		GraphicsContext gc = this.getGraphicsContext2D();
		mp = new MapPainterFX(gc);
        drawShapes(); 
	}
	void drawShapes() {
		printMap(this.getGraphicsContext2D());
	}
	void drawExplorersGeneralMap() {
		printGeneralExploerMap(this.getGraphicsContext2D());
	}
	
	/**
	 * 
	 * @param gc
	 */
	private void printMap(GraphicsContext gc) {
		mp.paint();
	}
	
	/**
	 * 
	 * @param gc
	 */
	private void printGeneralExploerMap(GraphicsContext gc) {
		mp.generalMapPainter();
	}
	
}



