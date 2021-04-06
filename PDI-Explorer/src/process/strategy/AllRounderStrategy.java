package process.strategy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import data.entity.Animal;
import data.entity.Entity;
import data.entity.Explorer;
import data.entity.LivingEntity;
import data.message.HelpMessage;
import data.message.MapMessage;
import data.message.Message;
import data.simulation.Environment;
import data.simulation.SimulationEntry;
import process.SimulationUtility;
import process.action.Action;
import process.action.CollectChestAction;
import process.action.ExplorationAction;
import process.action.LeaveMeAloneAction;
import process.action.RunAwayAction;
import process.action.SendMessageAction;
import process.manager.ExplorerManager;
import data.entity.Chest;

/**
 * 
 * @author Geoffroy
 * 
 *
 */
public class AllRounderStrategy extends ExplorationStrategy {

	private ExplorerManager em;
	private double[] position;
	private int scope;
	private ArrayList<LivingEntity> inScopeEntities;
	private ArrayList<Entity> inScopeObstacles;
	private PriorityList pl = new PriorityList();

	/* Basic priorities of this strategy */
	private static final int COLLECT_ACTION = 10;
	private static final int LEAVE_ME_ACTION = 8;
	private static final int RUN_AWAY = 6;
	private static final int SEND_MESSAGE = 4;
	private static final int EXPLORE_ACTION = 3;
	
	/* Modulate priority for messages */
	private static final int LOW_PRIORITY = 2;
	private static final int HIGH_PRIORITY = 9;
	
	
	/* Ponderate priorities of this strategy */
	//Note : CollectAction is set to be the highest level of priority in the simulation
	private int collect_action = COLLECT_ACTION;
	private int leave_me_action = LEAVE_ME_ACTION;
	private int run_away = RUN_AWAY; 
	private int send_message = SEND_MESSAGE;
	private int explore_action = EXPLORE_ACTION;
	
	public AllRounderStrategy(ExplorerManager explorerManager) {
		super(explorerManager);
		updateValues();
		setPriorities();
	}


	@Override
	public void decide() {
		updateValues();
		
		//Default operation :
		pl.addElement(explore_action, new ExplorationAction(getExplorerManager().getExplorer()));
		
		//If an obstacles is detected 
		if (!inScopeObstacles.isEmpty()) {
			for (Entity e : inScopeObstacles) {
				//If the obstacle is a chest
				if (e.getType().equals("Chest")) {
					pl.addElement(collect_action, new CollectChestAction((Chest) e, getExplorerManager().getExplorer()));
				
				}
			}
		}
		
		//If an entity is detected
		if(!inScopeEntities.isEmpty()) {
			for (LivingEntity le : inScopeEntities) {
				if (le.getType().equals("Explorer")) {
					// Trigger leave me alone action to make space between explorers
					pl.addElement(leave_me_action, new LeaveMeAloneAction(getExplorerManager().getExplorer(), (Explorer) le));
				} //Else Run away from a beast
				else {
					pl.addElement(run_away, new RunAwayAction(getExplorerManager().getExplorer(), (Animal) le));
				}
			}
		}
		
		
		//Sending message
		int sendMessageChance = 1 + (int)(Math.random() * ((300 - 1) + 1));
		//Send Message with a 1:99 chance
		if(sendMessageChance > 299) {
			pl.addElement(send_message, new SendMessageAction(new MapMessage(getExplorerManager().getExplorer().getMap(), getExplorerManager().getExplorer()),
					getExplorerManager().getExplorer()));
		}
		
		
		//Receiving Message
		ArrayList<Message> messages = getExplorerManager().getExplorer().getMessages();
		if(!messages.isEmpty()) {
			HelpMessage helpMe = (HelpMessage) messages.get(0);
			int helpPriority = calculatePrioritiesByDistance(helpMe.getContent());
			// TODO pl.addElement(helpPriority, new HelpAction);
		}
	
		
		Action action = pl.selectAction();
		pl.clear();
		super.planAction(action);
	}

	/**
	 * 
	 * @param senderPos
	 * @return
	 */
	public int calculatePrioritiesByDistance(double[] senderPos) {
		double distance = SimulationUtility.distance(getExplorerManager().getExplorer().getPosition(), senderPos);
		if (distance > 10) {
			return LOW_PRIORITY;
		}
		else {
			return HIGH_PRIORITY;
		}
	}
	
	
	/**
	 * @brief Update all in scope values for each tick of decide()
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
		ArrayList<Entity> entities = Environment.getInstance().getObstacles();
		ArrayList<Entity> inScope = new ArrayList<Entity>();
		for (Entity e : entities) {
			if(e != null) {
				if (SimulationUtility.distance(e.getPosition(), position) <= scope) {
					inScope.add(e);
				}
			}
		}
		return inScope;
	}
	
	/**
	 * @brief This function is used to read the strategies file and set corresponding priorities
	 */
	public void setPriorities() {
		BufferedReader br;
		String line = "";
		String separator = ";";
		String csvPath = "ressources/strategies.csv";
		int ct = 0;
		try {
			br = new BufferedReader(new FileReader(csvPath));
			br.readLine();
			while(((line = br.readLine()) != null) && ct != SimulationEntry.csvLine) {
				String field[] = line.split(separator);
				ct++;
				collect_action = Integer.parseInt(field[1]);
				leave_me_action = Integer.parseInt(field[2]);
				run_away = Integer.parseInt(field[3]);
				send_message = Integer.parseInt(field[4]);
				explore_action = Integer.parseInt(field[5]);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
