package tests.lucas;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


public class MapFX extends Canvas {

	protected static double TILE_SIZE = 10;
	protected static double width;
	protected static double height;
	private MapPainter2 mp;
	
	public MapFX(double arg1, double arg2) {
		super(arg1, arg2);
		this.width = arg1;
		this.height = arg2;
		TILE_SIZE = (arg2 - arg2/20)/90;
		GraphicsContext gc = this.getGraphicsContext2D();
		mp = new MapPainter2(gc);
        drawShapes(); 
	}
	void drawShapes() {
		printMap(this.getGraphicsContext2D());
	}
	void drawExplorersGeneralMap() {
		printGeneralExploerMap(this.getGraphicsContext2D());
	}
	
	private void printMap(GraphicsContext gc) {
		mp.paint();
	}
	
	private void printGeneralExploerMap(GraphicsContext gc) {
		mp.generalMapPainter();
	}
	
}



