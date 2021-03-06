package process.strategy;

import java.util.HashMap;

import process.action.Action;

/**
 * 
 * @author geoff
 * This class is used to determine the highest priority action.
 */

public class PriorityList {
	
	//Number of possible actions in the simulation
	private static final int MAX = 5;
	private static final int MIN = 1;

	private HashMap<Integer, Action> priorityMap = new HashMap<Integer, Action>();
	
		
	public void addElement(int priority, Action action) {
		if (!(priority < MIN || priority > MAX)) {
			priorityMap.put(priority, action);
		}
	}
	
	public Action selectAction() {
		if (!isEmpty()) {
			int num = MAX;
			while(!(priorityMap.containsKey(num))) { num--; }
			return priorityMap.get(num);
		}
		return null;
	}
	
	public boolean isEmpty() {
		return priorityMap.isEmpty();
	}
	
	public void clear() {
		priorityMap.clear();
	}
}
