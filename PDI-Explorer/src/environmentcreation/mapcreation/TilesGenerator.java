package environmentcreation.mapcreation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import data.map.Tile;

/**
 * transforms submaps' zones into finer tiles.
 * @author Léo
 *
 */
public class TilesGenerator {

	public static Tile[][] generation(char[][] zones){
		
		Tile tiles[][] = new Tile[90][90];
		for(int i = 0; i < 18; i ++)
			for(int j = 0; j < 18; j ++) {
				
				if(zones[i][j] == 'w') {
					for(int x = 0; x < 5; x ++)
						for(int y = 0; y < 5; y ++)
							tiles[(i*5)+x][(j*5)+y] = new Tile("w");
				} else {
					String shape = shapeCalculation(zones, i, j);
					int rotation = 0;
					if(!(shape.equals("gO") || shape.equals("gE"))) {
						rotation = rotationCalculation(zones, i, j, shape);
					}
					char[][] zone = openZone(shape);
					switch(rotation) {
					case 0 :
						writeRotZero(tiles, zone, i, j);
						break;
					case 1 :
						writeRotOne(tiles, zone, i, j);
						break;
					case 2 :
						writeRotTwo(tiles, zone, i, j);
						break;
					case 3 :
						writeRotThree(tiles, zone, i, j);
						break;
					}
				}
				
			}
		return tiles;
		
	}
	
	private static String shapeCalculation(char[][] zones, int i, int j) {
		String shape = String.valueOf(zones[i][j]);
		int gCount = 0;
		if(zones[i-1][j] == 'g')
			gCount ++;
		if(zones[i][j+1] == 'g')
			gCount ++;
		if(zones[i+1][j] == 'g')
			gCount ++;
		if(zones[i][j-1] == 'g')
			gCount ++;
		
		switch(gCount) {
		case 0 :
			return shape + "E";
		case 1 :
			return shape + "D";
		case 2 :
			if(zones[i-1][j] == zones[i+1][j])
				return shape + "I";
			else
				return shape + "L";
		case 3 :
			return shape + "V";
		case 4 :
			return shape + "O";
		}
		return shape;
	}
	
	private static int rotationCalculation(char[][] zones, int i, int j, String shape) {
		
		if(shape.equals("gI")) {
			if(zones[i-1][j] == 'g')
				return 0;
			else
				return 1;
		} else if(shape.equals("gL")) {
			if(zones[i-1][j] == 'g' && zones[i][j+1] == 'g')
				return 0;
			else if(zones[i+1][j] == 'g' && zones[i][j+1] == 'g')
				return 1;
			else if(zones[i+1][j] == 'g' && zones[i][j-1] == 'g')
				return 2;
			else if(zones[i-1][j] == 'g' && zones[i][j-1] == 'g')
				return 3;
		} else if(shape.equals("gD")) {
			if(zones[i-1][j] == 'g')
				return 0;
			else if(zones[i][j+1] == 'g')
				return 1;
			if(zones[i+1][j] == 'g')
				return 2;
			if(zones[i][j-1] == 'g')
				return 3;
		} else if(shape.equals("gV")) {
			if(zones[i-1][j] == 'w')
				return 0;
			else if(zones[i][j+1] == 'w')
				return 1;
			if(zones[i+1][j] == 'w')
				return 2;
			if(zones[i][j-1] == 'w')
				return 3;
		}
		return 0;
		
	}
	
	private static char[][] openZone(String shape){

		char[][] res = new char[5][5];
		try {
			String fileName = "ressources/zones/" + shape;
			if(!(shape.equals("gE") || shape.equals("gO")))
				fileName += (int)(Math.random()*2);
			fileName += ".csv";
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line;
			int i = 0;
			while((line = reader.readLine()) != null){
				String[] values = line.split(";");
				for(int j = 0; j < 5; j ++) {
					res[i][j] = values[j].charAt(0);
				}
				i ++;
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
	private static void writeRotZero(Tile[][] tiles, char[][] zone, int i, int j) {
		for(int x = 0; x < 5; x ++)
			for(int y = 0; y < 5; y ++)
				tiles[i*5+x][j*5+y] = new Tile(String.valueOf(zone[x][y]));
	}
	
	private static void writeRotOne(Tile[][] tiles, char[][] zone, int i, int j) {
		for(int x = 0; x < 5; x ++)
			for(int y = 0; y < 5; y ++)
				tiles[i*5+x][j*5+y] = new Tile(String.valueOf(zone[4-y][x]));
	}
	
	private static void writeRotTwo(Tile[][] tiles, char[][] zone, int i, int j) {
		for(int x = 0; x < 5; x ++)
			for(int y = 0; y < 5; y ++)
				tiles[i*5+x][j*5+y] = new Tile(String.valueOf(zone[4-x][4-y]));
	}
	
	private static void writeRotThree(Tile[][] tiles, char[][] zone, int i, int j) {
		for(int x = 0; x < 5; x ++)
			for(int y = 0; y < 5; y ++)
				tiles[i*5+x][j*5+y] = new Tile(String.valueOf(zone[y][4-x]));
	}
	
}
