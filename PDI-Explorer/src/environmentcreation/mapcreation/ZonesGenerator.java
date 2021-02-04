package environmentcreation.mapcreation;

public class ZonesGenerator {

	public static char[][] generation(SubMap[][] subMaps){
		
		for(int i = 0; i < 6; i ++)
			for(int j = 0; j < 6; j ++)
				subMapZonesGeneration(subMaps[i][j]);
		return zonesAssembling(subMaps);
		
	}
	
	private static void subMapZonesGeneration(SubMap subMap) {
		
		char[][] zone = new char[3][3];
		int top = subMap.getTop();
		int right = subMap.getRight();
		int bottom = subMap.getBottom();
		int left = subMap.getLeft();
		
		//creation of the top layer
		if(top >= 4)
			zone[0][0] = 'g';
		else
			zone[0][0] = 'w';
		if(top == 2 || top  == 3 || top == 6 || top == 7)
			zone[0][1] = 'g';
		else
			zone[0][1] = 'w';
		if(top%2 == 1)
			zone[0][2] = 'g';
		else
			zone[0][2] = 'w';
		
		//creation of the bottom layer
		if(bottom >= 4)
			zone[2][0] = 'g';
		else
			zone[2][0] = 'w';
		if(bottom == 2 || bottom == 3 || bottom == 6 || bottom == 7)
			zone[2][1] = 'g';
		else
			zone[2][1] = 'w';
		if(bottom%2 == 1)
			zone[2][2] = 'g';
		else
			zone[2][2] = 'w';
		
		//creation of the right layer
		if(right == 2 || right == 3 || right == 6 || right == 7)
			zone[1][2] = 'g';
		else
			zone[1][2] = 'w';
		
		//creation of the left layer
		if(left == 2 || left == 3 || left == 6 || left == 7)
			zone[1][0] = 'g';
		else
			zone[1][0] = 'w';
		
		//creation of the middle zone
		int gCount = 0;
		if(zone[0][1] == 'g')
			gCount ++;
		if(zone[1][2] == 'g')
			gCount ++;
		if(zone[2][1] == 'g')
			gCount ++;
		if(zone[1][0] == 'g')
			gCount ++;
		
		if(gCount >= 2)
			zone[1][1] = 'g';
		else
			zone[1][1] = 'w';
		
		subMap.setZones(zone);
	}

	private static char[][] zonesAssembling(SubMap[][] subMaps){
		
		char[][] zones = new char[18][18];
		for(int i = 0; i < 6; i ++)
			for(int j = 0; j < 6; j ++)
				for(int x = 0; x < 3; x ++)
					for(int y = 0; y < 3; y ++)
						zones[i*3+x][j*3+y] = subMaps[i][j].getZones()[x][y];
		return zones; 
		
	}
}
