package fr.univ.lille.fil.mbprestservice.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entité représentant le type Salle définit par
 * un nom, une capacité et un lieu
 * @author Rem
 *
 */
@Entity
@Table(name="salle")
public class Salle implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int sid;
	private String nom;
	private int capacite;
	
	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "lid")
	private Lieu lid;

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

	public Lieu getLid() {
		return lid;
	}

	public void setLid(Lieu lid) {
		this.lid = lid;
	}
	
	
	
}
