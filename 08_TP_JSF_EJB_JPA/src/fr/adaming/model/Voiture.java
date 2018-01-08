package fr.adaming.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="voitures")
public class Voiture implements Serializable{

	// Declaration des attributs
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_v")
	private int id;
	private String marque;
	private String type;
	private int numSerie;

	// transformation de l'association UML en java
	@ManyToOne
	@JoinColumn(name="agent_id",referencedColumnName="id_a")
	private Agent agent;
	
	// Declaration des constructeurs
	public Voiture() {
		super();
	}

	public Voiture(String marque, String type, int numSerie) {
		super();
		this.marque = marque;
		this.type = type;
		this.numSerie = numSerie;
	}

	public Voiture(int id, String marque, String type, int numSerie) {
		super();
		this.id = id;
		this.marque = marque;
		this.type = type;
		this.numSerie = numSerie;
	}

	// Declarations des Setters et Getters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getNumSerie() {
		return numSerie;
	}

	public void setNumSerie(int numSerie) {
		this.numSerie = numSerie;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	
	
	

}
