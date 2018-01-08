package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Agent;
import fr.adaming.model.Voiture;

@Local
public interface IVoitureDao {
	
	public List<Voiture> getAllVoitures(Agent a);
	
	public Voiture addVoiture(Voiture v);
	
	public Voiture updateVoiture(Voiture v);
	
	public void deleteVoiture(int id,Agent a);
	
	public Voiture getVoitureById(int id,Agent a);
	
	

}
