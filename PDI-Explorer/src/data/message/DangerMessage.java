package data.message;

import java.util.Arrays;

public class DangerMessage extends Message {
	private double[] dangerPosition;

	public DangerMessage(String message, double[] dangerPosition) {
		super(message);
		this.dangerPosition = dangerPosition;
	}

	public double[] getDangerPosition() {
		return dangerPosition;
	}

	public void setDangerPosition(double[] dangerPosition) {
		this.dangerPosition = dangerPosition;
	}

	@Override
	public String toString() {
		return message + "[" + Arrays.toString(dangerPosition) + "]";
	}

}