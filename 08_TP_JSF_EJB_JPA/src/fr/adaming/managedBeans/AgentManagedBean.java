package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Agent;
import fr.adaming.model.Voiture;
import fr.adaming.service.IAgentService;
import fr.adaming.service.IVoitureService;

@ManagedBean(name = "aMB")
@RequestScoped
public class AgentManagedBean implements Serializable {

	@EJB
	private IAgentService agentService;

	@EJB
	private IVoitureService voitureService;

	private Agent agent;

	private List<Voiture> listeVoitures;

	public AgentManagedBean() {
		this.agent = new Agent();
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public List<Voiture> getListeVoitures() {
		return listeVoitures;
	}

	public void setListeVoitures(List<Voiture> listeVoitures) {
		this.listeVoitures = listeVoitures;
	}

	public void setAgentService(IAgentService agentService) {
		this.agentService = agentService;
	}

	public void setVoitureService(IVoitureService voitureService) {
		this.voitureService = voitureService;
	}

	public String seConnecter() {

		try {
			Agent aOut = agentService.isExist(this.agent);

			// recup de la liste des voitures
			List<Voiture> listeVoitures = voitureService.getAllVoitures(aOut);

			// ajouter la liste dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("voituresList", listeVoitures);

			// ajouter l'agent dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("agentSession", aOut);

			return "success";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'agent n'existe pas"));
		}
		return "failure";

	}

}
