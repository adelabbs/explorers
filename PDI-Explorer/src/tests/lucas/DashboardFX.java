package tests.lucas;

import data.entity.Entity;
import data.entity.LivingEntity;
import data.simulation.Environment;
import environmentcreation.EnvironmentCreator;
import environmentcreation.event.EntityCreationException;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


public class DashboardFX extends Canvas {

	public static double TILE_SIZE = 10;
	
	public DashboardFX(double arg1, double arg2) {
		super(arg1, arg2);
		TILE_SIZE = arg2 /90;
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



