package fr.univ.lille.fil.mbprestservice.dto;

import java.util.Date;

import fr.univ.lille.fil.mbprestservice.entity.Salle;
import fr.univ.lille.fil.mbprestservice.entity.TypeSeance;
import fr.univ.lille.fil.mbprestservice.enumeration.Niveau;
import fr.univ.lille.fil.mbprestservice.enumeration.Sexe;

/**
 * Objet de transfert pour la liste des annonces
 * @author Théo
 *
 */
public class ListAdvertItemDTO {

	private int aid;
	private String nom;
	private String niveau;
	private String description;
	private String duree;
	private String date;
	private String type;
	private String sexAnnonceur;
	private String salleAnnonceur;
	
	public ListAdvertItemDTO(){
		super();
	}
	
	public ListAdvertItemDTO(int aid, String nomAnnonce,String description, String date, String duree, String typeSeance, String niveau, String sex, String salle){
		super();
		this.aid = aid;
		this.nom = nomAnnonce;
		this.description = description;
		this.date = date.toString();
		this.duree = duree+"";
		this.type = typeSeance;
		this.niveau = niveau;
		this.sexAnnonceur = sex;
		this.salleAnnonceur = salle;
	}
	
	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getNiveau() {
		return niveau;
	}
	
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDuree() {
		return duree;
	}

	public void setDuree(String duree) {
		this.duree = duree;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getSexAnnonceur() {
		return sexAnnonceur;
	}

	public void setSexAnnonceur(String sexAnnonceur) {
		this.sexAnnonceur = sexAnnonceur;
	}
	
	public String getSalleAnnonceur() {
		return salleAnnonceur;
	}

	public void setSalleAnnonceur(String salleAnnonceur) {
		this.salleAnnonceur = salleAnnonceur;
	}
	
}
