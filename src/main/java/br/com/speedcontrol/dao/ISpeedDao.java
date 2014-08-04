package br.com.speedcontrol.dao;

import java.sql.Timestamp;
import java.util.List;

import br.com.speedcontrol.model.SpeedRadar;

public interface ISpeedDao {
	
	public void registerSpeed(Timestamp time, float speed, List<String> tags);
	public List<SpeedRadar> retrieveSpeeds();
	public List<SpeedRadar> retrieveFinedSpeeds();
	public List<SpeedRadar> retrieveNotFinedSpeeds();

}
