package fr.univ.lille.fil.mbprestservice.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="salle")
public class Gym {

	
	@Id
	@GeneratedValue
	private int sid;
	private String nom;
	private int capacite;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "lid")
	private Place lid;

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	public Place getLid() {
		return lid;
	}

	public void setLid(Place lid) {
		this.lid = lid;
	}
	
	
	
}
