package fr.univ.lille.fil.mbprestservice.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "annonce")
public class Advert implements Serializable {

	private static final long serialVersionUID = 5878622546354363464L;

	@Id
	@GeneratedValue
	private int aid;

	private String description;

	@Column(name = "niveau_pratique", length = 10)
	private String niveauPratique;

	@Column(name = "duree_seance")
	private int dureeSeance;

	private String nom;

	@Column(name = "date_seance")
	private Date dateSeance;

	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "id_seance")
	private TypeSeance idSeance;

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNiveauPratique() {
		return niveauPratique;
	}

	public void setNiveauPratique(String niveauPratique) {
		this.niveauPratique = niveauPratique;
	}

	public int getDureeSeance() {
		return dureeSeance;
	}

	public void setDureeSeance(int dureeSeance) {
		this.dureeSeance = dureeSeance;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDateSeance() {
		return dateSeance;
	}

	public void setDateSeance(Date dateSeance) {
		this.dateSeance = dateSeance;
	}

	public TypeSeance getIdSeance() {
		return idSeance;
	}

	public void setIdSeance(TypeSeance idSeance) {
		this.idSeance = idSeance;
	}

}
