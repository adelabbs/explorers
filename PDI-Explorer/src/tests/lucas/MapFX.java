package tests.lucas;

import data.entity.Entity;

import data.entity.LivingEntity;
import data.simulation.Environment;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


public class MapFX extends Canvas {

	protected static double TILE_SIZE = 10;
	protected static double width;
	protected static double height;
	
	private PaintVisitor2 paintVisitor;
	private MapPainter2 mp;
	private Environment e = Environment.getInstance();

	
	public MapFX(double arg1, double arg2) {
		super(arg1, arg2);
		this.width = arg1;
		this.height = arg2;
		//System.out.println(arg1 + " " + arg2);
		TILE_SIZE = (arg2 - arg2/20)/90;
		GraphicsContext gc = this.getGraphicsContext2D();
		paintVisitor = new PaintVisitor2(gc);
		mp = new MapPainter2(gc);
        drawShapes(); 
	}
	void drawShapes() {
		printMap(this.getGraphicsContext2D());
	}
	
	private void printMap(GraphicsContext gc) {
		mp.paint();
		/*for(Entity entity : e.getEntities())
			entity.accept(paintVisitor);
		for(Entity entity : e.getObstacles())
			entity.accept(paintVisitor);		*/	
	}
	
}



