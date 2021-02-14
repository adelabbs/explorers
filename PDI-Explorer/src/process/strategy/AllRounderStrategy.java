package process.strategy;

import java.util.ArrayList;

import data.entity.Entity;
import data.entity.LivingEntity;
import data.simulation.Environment;
import process.action.ExplorerMoveAction;
import process.action.MoveAction;
import process.manager.ExplorerManager;

public class AllRounderStrategy extends ExplorationStrategy {
	
	private ExplorerManager em;
	private double[] position;
	private int scope;
	private ArrayList<LivingEntity> inScopeEntities;
	private ArrayList<Entity> inScopeObstacles;

	public AllRounderStrategy(ExplorerManager explorerManager) {
		super(explorerManager);
		updateValues();
	}
	
	@Override
	public void decide() { 
		//inScopeEntities.removeAll(inScopeEntities);
		updateValues();
		//If there is no living entity in scope then
		if(inScopeEntities.isEmpty() && inScopeObstacles.isEmpty()) {
			//Set random movement action
			MoveAction action = new ExplorerMoveAction(getExplorerManager().getExplorer(),
					Environment.getInstance());
			super.planAction(action);
		}
		else {
			int i = 0;
			for(LivingEntity le : inScopeEntities) {
				System.out.println("Aventurier"+i + le.getType());
				i++;
			}
			//Set random movement action
			MoveAction action = new ExplorerMoveAction(getExplorerManager().getExplorer(),
					Environment.getInstance());
			super.planAction(action);
		}
		
	}
	
	/**
	 * Update all in scope values for each tick of decide()
	 */
	public void updateValues() {
		em = getExplorerManager();
		position = em.getExplorer().getPosition();
		scope = em.getExplorer().getScope();
		inScopeEntities = getAllLivingEntitiesInScope(scope);
		inScopeObstacles = getAllObstaclesInScope(scope);
	}
	
	
	/**
	 * Get all living entities in explorer's scope
	 * @param scope
	 * @return
	 */
	public ArrayList<LivingEntity> getAllLivingEntitiesInScope(int scope){
		int dx = (int) position[0] + scope;
		int mdx = (int) position[0] - scope;
		int dy = (int) position[1] + scope;
		int mdy = (int) position[1] - scope;
		
		ArrayList<LivingEntity> entities = Environment.getInstance().getEntities();
		ArrayList<LivingEntity> inScope = new ArrayList<LivingEntity>();
		for (LivingEntity le : entities) {
			if(le.getPosition()[0] <= dx && le.getPosition()[0] >= mdx) {
				if(le.getPosition()[1] <= dy && le.getPosition()[1] >= mdy) {
					if(!(position == le.getPosition())) {
						inScope.add(le);
					}
				}
			}
		}
		return inScope;
	}
	
	/**
	 * Get all non living entities in explorer's scope
	 * @param scope
	 * @return
	 */
	public ArrayList<Entity> getAllObstaclesInScope(int scope){
		int dx = (int) position[0] + scope;
		int mdx = (int) position[0] - scope;
		int dy = (int) position[1] + scope;
		int mdy = (int) position[1] - scope;
		
		ArrayList<Entity> entities = Environment.getInstance().getObstacles();
		ArrayList<Entity> inScope = new ArrayList<Entity>();
		for (Entity e : entities) {
			if(e.getPosition()[0] <= dx && e.getPosition()[0] >= mdx) {
				if(e.getPosition()[1] <= dy && e.getPosition()[1] >= mdy) {
					if(!(position == e.getPosition())) {
						inScope.add(e);
					}
				}
			}
		}
		return inScope;
	}
}
