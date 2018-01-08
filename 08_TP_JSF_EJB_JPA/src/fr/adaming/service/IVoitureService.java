package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Agent;
import fr.adaming.model.Voiture;

@Local
public interface IVoitureService {

	public List<Voiture> getAllVoitures(Agent a);
	
	public Voiture addVoiture(Voiture v,Agent a);
	
	public Voiture updateVoiture(Voiture v,Agent a);
	
	public void deleteVoiture(int id,Agent a);
	
	public Voiture getVoitureById(int id,Agent a);
	
}
