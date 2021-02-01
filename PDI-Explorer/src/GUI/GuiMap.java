package GUI;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import data.entity.Explorer;
import data.map.Map;
import data.simulation.Environment;
import environmentcreation.EnvironmentCreator;
import environmentcreation.event.EntityCreationException;


public class GuiMap extends JPanel {

	private static final int TILE_SIZE = 10;
	private static Environment environment;
		
	public GuiMap() throws EntityCreationException {
		environment = EnvironmentCreator.creation(3, 0, 0);
	}
		
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i = 0; i < 90; i ++) {
			for(int j = 0; j < 90; j ++) {
				switch(environment.getMap().getTile(i, j).getType()) {
				case "w" :
					g.setColor(Color.BLUE);
					break;
				case "g" :
					g.setColor(Color.LIGHT_GRAY);
					break;
				}
				g.fillRect(j*TILE_SIZE, i*TILE_SIZE, TILE_SIZE, TILE_SIZE);
			}
		}
		
		g.setColor(Color.DARK_GRAY);
		for(int x = 0; x < environment.getExplorerAmount(); x ++) {
			Explorer e = environment.getExplorers().get(x);
			int i = (int) e.getPosition()[0];
			int j = (int) e.getPosition()[1];
			g.fillRect(j*TILE_SIZE, i*TILE_SIZE, TILE_SIZE, TILE_SIZE);
		}
		g.dispose();
	}
		
	public void deplacer(Explorer e) {	 
        int ran =(int) (Math.random()*4);
        double[] pos = e.getPosition();
        Map map = environment.getMap();
	    double xCheck = pos[0];
	    double yCheck = pos[1];
	    switch(ran)
	    	{
	        case 0:
	            //déplacement vers la droite
	         	xCheck++;
	          	//System.out.println("Droite");
	         	break;
	        case 1:
	                //déplacement vers la gauche
	          	xCheck--;
	            	//System.out.println("Gauche");
	            break;
	        case 2 :
	                //déplacement vers le haut
	        	yCheck--;
	            	//System.out.println("Haut");
	            break;
	        case 3 :
	                //déplacement vers le bas
	           	yCheck++;
	            	//System.out.println("Bas");
	           	break;
	       }
	        pos[0] = xCheck;
	        pos[1] = yCheck;
	        e.setPosition(pos);
	        }
	
	public static Environment getEnvironment() {
		return environment;
	}

	public static void setEnvironment(Environment environ) {
		environment = environ;
	}

	}