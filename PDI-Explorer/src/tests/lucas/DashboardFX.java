package tests.lucas;

import data.entity.Entity;

import data.entity.LivingEntity;
import data.simulation.Environment;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


public class DashboardFX extends Canvas {

	protected static double TILE_SIZE = 10;
	protected static double width;
	protected static double height;
	
	public DashboardFX(double arg1, double arg2) {
		super(arg1, arg2);
		this.width = arg1;
		this.width = arg2;
		System.out.println(arg1 + " " + arg2);
		TILE_SIZE = (arg2 - arg2/20)/90;
        drawShapes(); 
	}
	void drawShapes() {
		GraphicsContext gc = this.getGraphicsContext2D();
		printMap(gc);
		printLivingEntities(gc); 
	}

	private void printMap(GraphicsContext gc) {
		PaintVisitor2 pv = new PaintVisitor2(gc);
		MapPainter2 mp = new MapPainter2(gc);
		mp.paint();
			
		for(Entity entity : Environment.getInstance().getEntities())
			entity.accept(pv);
		for(Entity entity : Environment.getInstance().getObstacles())
			entity.accept(pv);			
	}

	private void printLivingEntities(GraphicsContext gc) {
		Environment e = Environment.getInstance();
		for (LivingEntity livingEntity : e.getEntities()) {
			PaintVisitor2 paintVisitor = new PaintVisitor2(gc);
			livingEntity.accept(paintVisitor);
		}
	}
}



