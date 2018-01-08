package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IAgentDao;
import fr.adaming.model.Agent;

@Stateful
public class AgentServiceImpl implements IAgentService {

	@EJB // pour injecter un dao
	private IAgentDao agentDao;

	//getters et setters pour l'injection de dependance
	public IAgentDao getAgentDao() {
		return agentDao;
	}

	public void setAgentDao(IAgentDao agentDao) {
		this.agentDao = agentDao;
	}

	@Override
	public Agent isExist(Agent a) throws Exception{
		
		return agentDao.isExist(a);
	}

}
