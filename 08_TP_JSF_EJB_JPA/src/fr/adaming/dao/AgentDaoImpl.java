package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Agent;

@Stateless
public class AgentDaoImpl implements IAgentDao{

	@PersistenceContext(unitName="PU_TP")// pour l'injection d'un em 
	EntityManager em;
	
	@Override
	public Agent isExist(Agent a) throws Exception {

		// construire la requete JPQL
		String req="SELECT a FROM Agent a WHERE a.mail=:pMail AND a.mdp=:pMdp";
		
		// Creer un query
		Query query=em.createQuery(req);
		
		// passaege des params
		query.setParameter("pMail", a.getMail());
		query.setParameter("pMdp", a.getMdp());
		
		// envoyer la requete et recup de l'agent
		Agent aOut=(Agent) query.getSingleResult();
		
		return aOut;
	}

}
