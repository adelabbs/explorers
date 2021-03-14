package tests.lucas;

import data.entity.Entity;

import data.entity.LivingEntity;
import data.simulation.Environment;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


public class DashboardFX extends Canvas {

	public static double TILE_SIZE = 10;
	
	private PaintVisitor2 paintVisitor;
	private MapPainter2 mp;

	
	public DashboardFX(double arg1, double arg2) {
		super(arg1, arg2);
		TILE_SIZE = (arg2 - arg2/20)/90;
		GraphicsContext gc = this.getGraphicsContext2D();
		paintVisitor = new PaintVisitor2(gc);
		mp = new MapPainter2(gc);
        drawShapes(); 
	}
	void drawShapes() {
		GraphicsContext gc = this.getGraphicsContext2D();
		printMap(gc);
	}

	private void printMap(GraphicsContext gc) {
		/*PaintVisitor2 pv = new PaintVisitor2(gc);
		MapPainter2 mp = new MapPainter2(gc);*/
		mp.paint();
		Environment e = Environment.getInstance();
		for(Entity entity : e.getEntities())
			entity.accept(paintVisitor);
		for(Entity entity : e.getObstacles())
			entity.accept(paintVisitor);			
	}
}



