package process.strategy;

import java.util.ArrayList;

import data.entity.Entity;
import data.entity.Explorer;
import data.entity.LivingEntity;
import data.simulation.Environment;
import process.SimulationUtility;
import process.action.Action;
import process.action.CollectChestAction;
import process.action.ExplorationAction;
import process.action.ExplorerMoveAction;
import process.action.LeaveMeAloneAction;
import process.action.MoveAction;
import process.manager.ExplorerManager;
import data.entity.Chest;

public class AllRounderStrategy extends ExplorationStrategy {

	private ExplorerManager em;
	private double[] position;
	private int scope;
	private ArrayList<LivingEntity> inScopeEntities;
	private ArrayList<Entity> inScopeObstacles;
	private PriorityList pl = new PriorityList();

	/* Priority of this strategy */
	private static final int COLLECT_ACTION = 5;
	private static final int LEAVE_ME_ACTION = 4;
	private static final int RUN_AWAY = 3;
	private static final int SEND_MESSAGE = 2;
	private static final int EXPLORE_ACTION = 1;
	
	public AllRounderStrategy(ExplorerManager explorerManager) {
		super(explorerManager);
		updateValues();
	}

	@Override
	public void decide() {
		updateValues();
		
		//Default operation :
		pl.addElement(EXPLORE_ACTION, new ExplorationAction(getExplorerManager().getExplorer()));
		
		//If an obstacles is detected 
		if (!inScopeObstacles.isEmpty()) {
			for (Entity e : inScopeObstacles) {
				//If the obstacle is a chest
				if (e.getType().equals("Chest")) {
					pl.addElement(COLLECT_ACTION, new CollectChestAction((Chest) e, getExplorerManager().getExplorer()));
				
				}
			}
		}
		if(!inScopeEntities.isEmpty()) {
			for (LivingEntity le : inScopeEntities) {
				if (le.getType().equals("Explorer")) {
					// Trigger leave me alone action to make space between explorers
					pl.addElement(LEAVE_ME_ACTION, new LeaveMeAloneAction(getExplorerManager().getExplorer(), (Explorer) le));
				} //Else Run away from a beast
				else {
					//TODO ADD RUN AWAY ACTION & IMPLEMENTATION
					//pl.addELement(RUN_AWAY, new RunAwayAction(getExplorerManager().getExplorer(), (Animal) le));
				}
			}
		}
		
		/*
		int sendMessageChance = 1 + (int)(Math.random() * ((500 - 1) + 1));
		//Send Message with a 1:500 chance
		if(sendMessageChance > 499) {
			//TODO
			pl.addElement(SEND_MESSAGE, null);
		}
		*/
		Action action = pl.selectAction();
		pl.clear();
		super.planAction(action);
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
	 * 
	 * @param scope
	 * @return
	 */
	public ArrayList<LivingEntity> getAllLivingEntitiesInScope(int scope) {
		int dx = (int) position[0] + scope;
		int mdx = (int) position[0] - scope;
		int dy = (int) position[1] + scope;
		int mdy = (int) position[1] - scope;

		ArrayList<LivingEntity> entities = Environment.getInstance().getEntities();
		ArrayList<LivingEntity> inScope = new ArrayList<LivingEntity>();
		for (LivingEntity le : entities) {
			if (le.getPosition()[0] <= dx && le.getPosition()[0] >= mdx) {
				if (le.getPosition()[1] <= dy && le.getPosition()[1] >= mdy) {
					if (!(position == le.getPosition())) {
						inScope.add(le);
					}
				}
			}
		}
		return inScope;
	}

	/**
	 * Get all non living entities in explorer's scope
	 * 
	 * @param scope
	 * @return
	 */
	public ArrayList<Entity> getAllObstaclesInScope(int scope) {
		/*
		 * int dx = (int) position[0] + scope; int mdx = (int) position[0] - scope; int
		 * dy = (int) position[1] + scope; int mdy = (int) position[1] - scope;
		 */

		ArrayList<Entity> entities = Environment.getInstance().getObstacles();
		ArrayList<Entity> inScope = new ArrayList<Entity>();
		for (Entity e : entities) {
			if (SimulationUtility.distance(e.getPosition(), position) <= scope) {
				inScope.add(e);
			}
			/*
			 * if (e.getPosition()[0] <= dx && e.getPosition()[0] >= mdx) { if
			 * (e.getPosition()[1] <= dy && e.getPosition()[1] >= mdy) { if (!(position ==
			 * e.getPosition())) {
			 * System.out.println(SimulationUtility.distance(e.getPosition(), position));
			 * inScope.add(e); } } }
			 */

		}
		return inScope;
	}
}
