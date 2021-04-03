package gui;

import data.entity.Entity;
import data.simulation.Environment;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


public class EntitiesFX extends Canvas {

	protected static double TILE_SIZE = 10;
	protected static double width;
	protected static double height;
	
	private PaintVisitor2 paintVisitor;
	private Environment e = Environment.getInstance();

	
	public EntitiesFX(double arg1, double arg2) {
		super(arg1, arg2);
		TILE_SIZE = (arg2 - arg2/20)/90;
		GraphicsContext gc = this.getGraphicsContext2D();
		paintVisitor = new PaintVisitor2(gc);
        drawShapes(); 
	}
	void drawShapes() {
		printMap(this.getGraphicsContext2D());
	}
	
	void clearShapes() {
		this.getGraphicsContext2D().clearRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	private void printMap(GraphicsContext gc) {
		for(Entity entity : e.getEntities())
			entity.accept(paintVisitor);
		for(Entity entity : e.getObstacles())
			entity.accept(paintVisitor);			
	}
	
}



