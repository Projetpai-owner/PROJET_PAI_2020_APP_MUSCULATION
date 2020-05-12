package fr.univ.lille.fil.mbprestservice.dto;

public class ListAdvertItemDTO {

	private int aid;
	private String prenom;
	private String nomAnnonce;
	private String description;
	private String date;
	private String duree;

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
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

}
