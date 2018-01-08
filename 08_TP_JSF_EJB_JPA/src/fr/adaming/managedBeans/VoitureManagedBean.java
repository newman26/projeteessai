package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Agent;
import fr.adaming.model.Voiture;
import fr.adaming.service.IVoitureService;

@ManagedBean(name = "vMB")
@RequestScoped
public class VoitureManagedBean implements Serializable {

	@EJB
	private IVoitureService voitureService;

	private Voiture voiture;

	private Agent agent;
	private List<Voiture> listeVoitures;

	private HttpSession maSession;

	public VoitureManagedBean() {
		this.voiture = new Voiture();
	}

	// methode qui s'execute apres l'instanciation du managedBean
	@PostConstruct
	public void init() {
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.agent = (Agent) maSession.getAttribute("agentSession");
	}

	public Voiture getVoiture() {
		return voiture;
	}

	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
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

	public void setVoitureService(IVoitureService voitureService) {
		this.voitureService = voitureService;
	}

	// les methodes metiers
	public String ajouterVoiture() {
		System.out.println("sssssss   " + agent.getId() + " vvvvvvvv" + voiture.getMarque());
		this.voiture = voitureService.addVoiture(this.voiture, this.agent);

		if (this.voiture.getId() != 0) {
			// recup de la nouvelle liste de la bd
			this.listeVoitures = voitureService.getAllVoitures(agent);
			// mettre à jour la liste dans la session
			maSession.setAttribute("voituresList", this.listeVoitures);

			return "success";

		} else {

			return "ajout";
		}

	}

	public String supprimerVoiture() {

		voitureService.deleteVoiture(this.voiture.getId(), this.agent);

		// recup la liste
		this.listeVoitures = voitureService.getAllVoitures(this.agent);

		// mise a jour de la liste
		maSession.setAttribute("voituresList", this.listeVoitures);

		return "success";
	}

	public String rechercheVoiture() {
		Voiture vFind = voitureService.getVoitureById(this.voiture.getId(), this.agent);

		if (vFind != null) {
			this.voiture = vFind;
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cette voiture n'existe pas !"));
		}

		return "recherche";
	}

	public String modifierVoiture() {
		Voiture vModif = voitureService.updateVoiture(this.voiture, this.agent);
		if (vModif != null) {
			// recup la liste
			this.listeVoitures = voitureService.getAllVoitures(this.agent);
			// mise a jour de la liste
			maSession.setAttribute("voituresList", this.listeVoitures);
			return "success";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La modification n'a pas fonctionné"));
			return "modif";
		}
	}

}
