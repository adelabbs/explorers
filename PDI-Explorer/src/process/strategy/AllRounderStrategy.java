package process.strategy;

import java.util.ArrayList;

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

	public AllRounderStrategy(ExplorerManager explorerManager) {
		super(explorerManager);
		updateValues();
	}
	
	@Override
	public void decide() { 
		updateValues();
		//If there is no living entity in scope then
		if(inScopeEntities.isEmpty()) {
			//Set random movement action
			MoveAction action = new ExplorerMoveAction(getExplorerManager().getExplorer(),
					Environment.getInstance());
			super.planAction(action);
		}
		else {
			System.out.println(inScopeEntities.get(0).getType());
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
		inScopeEntities = getAllEntitiesInScope(scope);
	}
	
	
	/**
	 * Get all living entities in the explorer's scope
	 * @param scope
	 * @return
	 */
	public ArrayList<LivingEntity> getAllEntitiesInScope(int scope){
		int dx = (int) position[0] + scope;
		int mdx = (int) position[0] - scope;
		int dy = (int) position[1] + scope;
		int mdy = (int) position[1] - scope;
		
		ArrayList<LivingEntity> entities = Environment.getInstance().getEntities();
		ArrayList<LivingEntity> inScope = new ArrayList<LivingEntity>();
		for (LivingEntity le : entities) {
			if(le.getPosition()[0] <= dx && le.getPosition()[1] >= mdx) {
				if(le.getPosition()[1] <= dy && le.getPosition()[1] >= mdy) {
					inScope.add(le);
				}
			}
		}
		return inScope;
	}
}
