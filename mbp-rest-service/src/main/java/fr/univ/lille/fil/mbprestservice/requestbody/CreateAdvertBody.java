package fr.univ.lille.fil.mbprestservice.requestbody;

import java.sql.Date;

public class CreateAdvertBody {

	private String description;
	private String niveauPratique;
	private int dureeSeance;
	private String nom;
	private Date dateSeance;
	private int idSeance;

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

	public int getIdSeance() {
		return idSeance;
	}

	public void setIdSeance(int idSeance) {
		this.idSeance = idSeance;
	}

}
