package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Agent;
import fr.adaming.model.Voiture;

@Stateless
public class VoitureDaoImpl implements IVoitureDao {

	@PersistenceContext(unitName = "PU_TP")
	private EntityManager em;

	// setters pour l'injection de dependance
	public void setEm(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Voiture> getAllVoitures(Agent a) {

		// construire la requete JPQL
		String req = "SELECT v FROM Voiture as v where v.agent.id=:pIdA";

		// creer le query
		Query query = em.createQuery(req);

		// passage des params
		query.setParameter("pIdA", a.getId());

		// envoyer la requete et recup du resulat
		return query.getResultList();
	}

	@Override
	public Voiture addVoiture(Voiture v) {
		em.persist(v);
		return v;
	}

	@Override
	public Voiture updateVoiture(Voiture v) {
		em.merge(v);
		return v;
	}

	@Override
	public void deleteVoiture(int id, Agent a) {
		//creation requete JPQL
		String req="DELETE FROM Voiture as v WHERE v.id=:pId AND v.agent.id=:pAgent";
		
		//creer query
		Query query=em.createQuery(req);
		
		//passage des params
		query.setParameter("pId", id);
		query.setParameter("pAgent", a.getId());
		
		//envoyer et recup la req
		query.executeUpdate();

	}

	@Override
	public Voiture getVoitureById(int id, Agent a) {
		//création d'une requete JPQL
		String req = "select v from Voiture as v where v.id=:pIdVoiture and v.agent.id=:pIdAgent";
		
		//Création du query
		Query query = em.createQuery(req);
		
		//assignation des paramètres de la requete
		query.setParameter("pIdVoiture", id);
		query.setParameter("pIdAgent", a.getId());
		
		try {
			//envoi de la requete et récupération du résultat
			Voiture vFind = (Voiture) query.getSingleResult();
			return vFind;
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
