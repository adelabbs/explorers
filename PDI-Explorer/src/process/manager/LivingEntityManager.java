package process.manager;

import data.entity.LivingEntity;

/**
 * The abstract manager type. Each manager is using a thread.
 *
 */
public abstract class LivingEntityManager extends Thread {

	private boolean running = false;

	public abstract void updatePosition(double[] newPosition);

	public abstract void updateHealth(int newHealth);

	public abstract LivingEntity getEntity();

	public boolean isDead() {
		return getEntity().getHealth() <= 0;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean isRunning) {
		this.running = isRunning;
	}

}