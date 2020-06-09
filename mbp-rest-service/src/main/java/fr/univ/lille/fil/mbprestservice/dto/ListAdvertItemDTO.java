package fr.univ.lille.fil.mbprestservice.dto;

import java.util.Date;

import fr.univ.lille.fil.mbprestservice.entity.Salle;
import fr.univ.lille.fil.mbprestservice.entity.TypeSeance;
import fr.univ.lille.fil.mbprestservice.enumeration.Niveau;
import fr.univ.lille.fil.mbprestservice.enumeration.Sexe;

public class ListAdvertItemDTO {

	private int aid;
	private String prenom;
	private String nomAnnonce;
	private String description;
	private String date;
	private String duree;
	private String typeSeance;
	private String niveauSeance;
	private String sexAnnonceur;
	private String salleAnnonceur;
	
	public ListAdvertItemDTO(){
		super();
	}
	
	/*public ListAdvertItemDTO(String aid, String nomAnnonce, String description, String date, String duree,String typeSeance,String niveauSeance){
		super();
		this.aid = Integer.parseInt(aid);
		this.nomAnnonce = nomAnnonce;
		this.description = description;
		this.date = date;
		this.duree = duree;
		this.typeSeance = typeSeance;
		this.niveauSeance = niveauSeance;
	}*/
	
	public ListAdvertItemDTO(int aid, String nomAnnonce,String description, Date date, int duree, TypeSeance typeSeance, Niveau niveau,Sexe sex,Salle salle){
		super();
		this.aid = aid;
		this.nomAnnonce = nomAnnonce;
		this.description = description;
		this.date = date.toString();
		this.duree = duree+"";
		this.typeSeance = typeSeance.getLibelle();
		this.niveauSeance = niveau.toString();
		this.sexAnnonceur = sex.toString();
		this.salleAnnonceur = salle.getSid()+"";
	}

	public String getSexAnnonceur() {
		return sexAnnonceur;
	}

	public void setSexAnnonceur(String sexAnnonceur) {
		this.sexAnnonceur = sexAnnonceur;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTypeSeance() {
		return typeSeance;
	}

	public void setTypeSeance(String typeSeance) {
		this.typeSeance = typeSeance;
	}

	public String getNiveauSeance() {
		return niveauSeance;
	}

	public void setNiveauSeance(String niveauSeance) {
		this.niveauSeance = niveauSeance;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getNomAnnonce() {
		return nomAnnonce;
	}

	public void setNomAnnonce(String nomAnnonce) {
		this.nomAnnonce = nomAnnonce;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDuree() {
		return duree;
	}

	public void setDuree(String duree) {
		this.duree = duree;
	}

	public String getSalleAnnonceur() {
		return salleAnnonceur;
	}

	public void setSalleAnnonceur(String salleAnnonceur) {
		this.salleAnnonceur = salleAnnonceur;
	}
	
}
