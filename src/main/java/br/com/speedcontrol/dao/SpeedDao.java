package br.com.speedcontrol.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.speedcontrol.configuration.Configuration;
import br.com.speedcontrol.model.SpeedRadar;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.eaio.uuid.UUID;

public class SpeedDao implements ISpeedDao {

	public void registerSpeed(Timestamp time, float speed, List<String> tags) {
		UUID id = new UUID();
		
		String tagString;
		
		if (tags.isEmpty()) {
			tagString = "{}";
		} else {
		
			tagString = "{";
			
			for (String tag : tags) {
				tagString = tagString+"'"+tag+"',";
			}
			
			tagString = tagString.substring(0, tagString.length()-1);
			tagString = tagString +"}";
			System.out.println("TagString = "+tagString);
		}
		
		
		String query = "INSERT INTO speedcontrol.speed (id, time, speed, tags) " +
			      "VALUES (" +
		          ""+id+"," +
		          "'"+time+"'," +
		          ""+speed+"," +
		          ""+tagString+")" +
		          ";";
		
		Configuration.getSession().execute(query);
		
	}

	public List<SpeedRadar> retrieveSpeeds() {
		ResultSet results = Configuration.getSession().execute("SELECT * FROM speedcontrol.speed;");

		//System.out.println(String.format("%-40s\t%-20s\t%-20s\n%s", "id", "speed", "time",
		//	       "-----------------------------------+-----------------------+--------------------"));
		
		List<SpeedRadar> speedList = new ArrayList<SpeedRadar>();
		
		for (Row row : results) {
			//System.out.println(String.format("%-40s\t%-20s\t%-20s", row.getUUID("id"),
			//row.getFloat("speed"), new Timestamp(row.getDate("time").getTime())).toString());
			
			SpeedRadar speedRadar = new SpeedRadar();
			speedRadar.setSpeed(row.getFloat("speed"));
			speedRadar.setTime(new Timestamp(row.getDate("time").getTime()));
			
			speedList.add(speedRadar);
		}
		
		return speedList;
	}

}
