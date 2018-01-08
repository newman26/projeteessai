package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IVoitureDao;
import fr.adaming.model.Agent;
import fr.adaming.model.Voiture;

@Stateful
public class VoitureServiceImpl implements IVoitureService {

	@EJB
	private IVoitureDao voitureDao;

	public void setVoitureDao(IVoitureDao voitureDao) {
		this.voitureDao = voitureDao;
	}

	@Override
	public List<Voiture> getAllVoitures(Agent a) {

		return voitureDao.getAllVoitures(a);
	}

	@Override
	public Voiture addVoiture(Voiture v, Agent a) {
		// lier l'agent à la voiture
		v.setAgent(a);

		return voitureDao.addVoiture(v);
	}

	@Override
	public Voiture updateVoiture(Voiture v, Agent a) {
		Voiture vModif = voitureDao.getVoitureById(v.getId(), a);
		if (vModif != null) {
			v.setAgent(a);
			return voitureDao.updateVoiture(v);
		} else {
			return null;
		}
	}

	@Override
	public void deleteVoiture(int id, Agent a) {
		voitureDao.deleteVoiture(id, a);

	}

	@Override
	public Voiture getVoitureById(int id, Agent a) {
		return voitureDao.getVoitureById(id, a);
	}

}
