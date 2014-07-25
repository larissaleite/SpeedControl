package br.com.speedcontrol.main;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.speedcontrol.configuration.Configuration;
import br.com.speedcontrol.dao.ISpeedDao;
import br.com.speedcontrol.dao.SpeedDao;

public class Main {
	
	public static float speedGenerator() {
		float minX = 10.0f;
		float maxX = 200.0f;

		Random rand = new Random();

		float finalX = rand.nextFloat() * (maxX - minX) + minX;
		
		return finalX;
	}
	
	public static Timestamp getCurrentTime() {
		 java.util.Date date= new java.util.Date();
		 return new Timestamp(date.getTime());
	}
	
	public static void main(String[] args) {
		ISpeedDao speedDao = new SpeedDao();
		
		for (int i=0; i<100; i++) {
			float speed = speedGenerator();
			List<String> tags = new ArrayList<String>();
			
			if (speed > 50) {
				tags.add("fined");
			} else {
				tags.add("not fined");
			}
			
			//System.out.println("Registering : "+speed);
			speedDao.registerSpeed(getCurrentTime(), speed, tags);
		}
		
		//speedDao.retrieveSpeeds();
		
		Configuration.close();
		
	}
	
}
