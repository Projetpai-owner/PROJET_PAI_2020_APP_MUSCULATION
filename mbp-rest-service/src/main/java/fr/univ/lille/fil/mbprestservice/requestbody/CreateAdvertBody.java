package fr.univ.lille.fil.mbprestservice.requestbody;

import java.sql.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import fr.univ.lille.fil.mbprestservice.enumeration.Niveau;

public class CreateAdvertBody {

	private String description;
	@Enumerated(EnumType.STRING)
	private Niveau niveauPratique;
	private int dureeSeance;
	private String nom;
	private Date dateSeance;
	private int idSeance;
	private int idUser;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Niveau getNiveauPratique() {
		return niveauPratique;
	}

	public void setNiveauPratique(Niveau niveauPratique) {
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

	public int getIdSeance() {
		return idSeance;
	}

	public void setIdSeance(int idSeance) {
		this.idSeance = idSeance;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	

}
