package fr.univ.lille.fil.mbprestservice.dto;

/**
 * Classe de mapping pour un ami
 * @author Anthony Bliecq
 *
 */
public class AmiDTO {

	private int pid;
	private String nom;
	private String prenom;
	private String username;
	
	public int getPid() {
		return pid;
	}
	public String getNom() {
		return nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public String getUsername() {
		return username;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	
}
