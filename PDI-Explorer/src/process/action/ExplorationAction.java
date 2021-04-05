package process.action;

import data.entity.Entity;
import data.entity.Explorer;
import data.map.ExplorerMap;
import data.message.MapMessage;
import data.simulation.Environment;

/**
 * This class is the basic non random movement of an explorer.
 * The explorer will move toward an undiscovered destination.
 *
 */
public class ExplorationAction implements Action {

	private Explorer explorer;
	private ExplorerMoveAction ema;
	
	public ExplorationAction(Explorer explorer) {
		this.explorer = explorer;
	}
	
	public void execute() {
		int range = explorer.getScope() + 1;
		ExplorerMap explorerMap = explorer.getMap();
		int x = (int) explorer.getPosition()[0];
		int y = (int) explorer.getPosition()[1];
		boolean move = false;
		int distMax = 150;
		int tempI = 0;
		int tempJ = 0;
		while(!move) {
			if(range < distMax) {
				int dir = (int) (Math.random()*4);
				switch(dir) {
				case 0: //starts top left
					for(int i = x - range/2; i < x + range/2; i ++) {
						for(int j = y - range/2; j < y + range/2; j ++) {
							if(!oob(i, j)) {
								if(!explorerMap.getTile(i, j).isExplored() && isNotWaterBoarded(explorerMap, i, j)) {
									int tempX = x - i;
									int tempY = y - j;
									int distance = distance(tempX, tempY);
									if(distance <= distMax) {
										int rand = (int) (Math.random()*2);
										if(distance <= distMax || rand == 1) {
											tempI = i;
											tempJ = j;
											if(distance < distMax)
												distMax = distance;
										}
									}
									move = true;
								}
							}
						}
					}
					break;
				case 1: //starts top right
					for(int j = y - range/2; j < y + range/2; j ++) {
						for(int i = x + range/2; i > x - range/2; i --) {
							if(!oob(i, j)) {
								if(!explorerMap.getTile(i, j).isExplored() && isNotWaterBoarded(explorerMap, i, j)) {
									int tempX = x - i;
									int tempY = y - j;
									int distance = distance(tempX, tempY);
									if(distance <= distMax) {
										int rand = (int) (Math.random()*2);
										if(distance <= distMax || rand == 1) {
											tempI = i;
											tempJ = j;
											if(distance < distMax)
												distMax = distance;
										}
									}
									move = true;
								}
							}
						}
					}
					break;
				case 2: //starts bottom right
					for(int i = x + range/2; i > x - range/2; i --) {
						for(int j = y + range/2; j > y + range/2; j --) {
							if(!oob(i, j)) {
								if(!explorerMap.getTile(i, j).isExplored() && isNotWaterBoarded(explorerMap, i, j)) {
									int tempX = x - i;
									int tempY = y - j;
									int distance = distance(tempX, tempY);
									if(distance <= distMax) {
										int rand = (int) (Math.random()*2);
										if(distance <= distMax || rand == 1) {
											tempI = i;
											tempJ = j;
											if(distance < distMax)
												distMax = distance;
										}
									}
									move = true;
								}
							}
						}
					}
					break;
				case 3: //starts bottom left
					for(int j = y + range/2; j > y - range/2; j --) {
						for(int i = x - range/2; i < x + range/2; i ++) {
							if(!oob(i, j)) {
								if(!explorerMap.getTile(i, j).isExplored() && isNotWaterBoarded(explorerMap, i, j)) {
									int tempX = x - i;
									int tempY = y - j;
									int distance = distance(tempX, tempY);
									if(distance <= distMax) {
										int rand = (int) (Math.random()*2);
										if(distance <= distMax || rand == 1) {
											tempI = i;
											tempJ = j;
											if(distance < distMax)
												distMax = distance;
										}
									}
									move = true;
								}
							}
						}
					}
					break;
				}
				
				
			} else {
				SendMessageAction sma = new SendMessageAction(new MapMessage(explorer.getMap(), explorer), explorer);
				sma.execute();
				range = explorer.getScope();
			}
			range ++;
		}
		createMoveAction(tempI, tempJ);
	}
	
	private boolean isNotWaterBoarded(ExplorerMap explorerMap, int i, int j) {
		if(i == 0 || i == 89 || j == 0 || j == 89) 
			return false;
		else
			return(explorerMap.getTile(i-1, j).getType().equals("g") || 
				explorerMap.getTile(i, j-1).getType().equals("g") || 
				explorerMap.getTile(i+1, j).getType().equals("g") || 
				explorerMap.getTile(i, j+1).getType().equals("g"));
	}

	private void createMoveAction(int i, int j) {
		int fPosX = (int) explorer.getPosition()[0];
		int fPosY = (int) explorer.getPosition()[1];
		int vx = i - fPosX;
		int vy = j - fPosY;
		if(Math.abs(vx) > Math.abs(vy)) {
			if(vx < 0)
				fPosX --;
			else
				fPosX ++;
		} else {
			if(vy < 0)
				fPosY --;
			else
				fPosY ++;
		}
		if(Math.abs(vx) > Math.abs(vy)) {
			if(thereIsAnObstacle(fPosX, fPosY)) {
				if(vy < 0)
					ema = new ExplorerMoveAction(explorer, Environment.getInstance(), MoveAction.WEST);
				else
					ema = new ExplorerMoveAction(explorer, Environment.getInstance(), MoveAction.EAST);
			} else {
				if(vx < 0)
					ema = new ExplorerMoveAction(explorer, Environment.getInstance(), MoveAction.NORTH);
				else
					ema = new ExplorerMoveAction(explorer, Environment.getInstance(), MoveAction.SOUTH);
			}
		} else {
			if(thereIsAnObstacle(fPosX, fPosY)) {
				if(vx < 0)
					ema = new ExplorerMoveAction(explorer, Environment.getInstance(), MoveAction.NORTH);
				else
					ema = new ExplorerMoveAction(explorer, Environment.getInstance(), MoveAction.SOUTH);
			} else {
				if(vy < 0)
					ema = new ExplorerMoveAction(explorer, Environment.getInstance(), MoveAction.WEST);
				else
					ema = new ExplorerMoveAction(explorer, Environment.getInstance(), MoveAction.EAST);
			}
		}
		ema.execute();
	}
	
	private boolean thereIsAnObstacle(int i, int j) {
		int posX, posY, sizeX, sizeY;
		for(Entity obstacle : Environment.getInstance().getObstacles()) {
			posX = (int) obstacle.getPosition()[0];
			posY = (int) obstacle.getPosition()[1];
			sizeX = (int) obstacle.getSize()[0];
			sizeY = (int) obstacle.getSize()[1];
			if(i <= posX + sizeX + 0.5 && i >= posX - 0.5 && j <= posY + sizeY + 0.5 && j >= posY - 0.5)
				return true;
		}
		return false;
	}
	
	private boolean oob(int i, int j) {
		return i >= 90 || j >= 90 || i < 0 || j < 0;
	}
	
	private int distance(int i, int j) {
		return (int) (Math.sqrt(Math.pow((double) i - explorer.getPosition()[0], 2) + Math.pow((double) i - explorer.getPosition()[0], 2)));
	}
	
	public void createUndiscoveredMoveAction(int i, int j) {
		//South
		if(explorer.getPosition()[1] < i) {
			ema = new ExplorerMoveAction(explorer, Environment.getInstance(), MoveAction.SOUTH);
			ema.execute();
		}
		//North
		else if(explorer.getPosition()[1] > i) { 
			ema = new ExplorerMoveAction(explorer, Environment.getInstance(), MoveAction.NORTH);
			ema.execute();
		}
		//East
		else if(explorer.getPosition()[0] < j) {
			ema = new ExplorerMoveAction(explorer, Environment.getInstance(), MoveAction.EAST);
			ema.execute();
		}
		//West
		else if(explorer.getPosition()[0] > j) {
			ema = new ExplorerMoveAction(explorer, Environment.getInstance(), MoveAction.WEST);
			ema.execute();
		}
	}
	
	@Override
	public boolean isOver() {
		return true;
	}

}
