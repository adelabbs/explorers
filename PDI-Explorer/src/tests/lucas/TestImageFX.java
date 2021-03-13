package tests.lucas;

import data.entity.Entity;
import data.entity.LivingEntity;
import data.simulation.Environment;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class TestImageFX extends Canvas {

		public static final double TILE_SIZE = 10;
		
		public void start(Stage stage) {
			Group root = new Group(this);
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		
		public TestImageFX() {
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
