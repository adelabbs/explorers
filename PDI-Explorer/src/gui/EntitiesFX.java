package gui;

import data.entity.Entity;
import data.simulation.Environment;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * 
 * @author lespi
 *
 */
public class EntitiesFX extends Canvas {

	protected static double TILE_SIZE = 10;
	protected static double width;
	protected static double height;
	
	private PaintVisitorFX paintVisitor;
	private Environment e = Environment.getInstance();

	/**
	 * 
	 * @param arg1
	 * @param arg2
	 */
	public EntitiesFX(double arg1, double arg2) {
		super(arg1, arg2);
		TILE_SIZE = (arg2 - arg2/20)/90;
		GraphicsContext gc = this.getGraphicsContext2D();
		paintVisitor = new PaintVisitorFX(gc);
        drawShapes(); 
	}
	void drawShapes() {
		printMap(this.getGraphicsContext2D());
	}
	
	void clearShapes() {
		this.getGraphicsContext2D().clearRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	/**
	 * 
	 * @param gc
	 */
	private void printMap(GraphicsContext gc) {
		for(Entity entity : e.getEntities())
			entity.accept(paintVisitor);
		for(Entity entity : e.getObstacles())
			entity.accept(paintVisitor);			
	}
	
}



