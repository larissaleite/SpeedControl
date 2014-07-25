package br.com.speedcontrol.model;

import java.sql.Timestamp;

public class SpeedRadar {
	
	private Timestamp time;
	private float speed;
	
	public float getSpeed() {
		return speed;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public Timestamp getTime() {
		return time;
	}
	
	public void setTime(Timestamp time) {
		this.time = time;
	}

}
