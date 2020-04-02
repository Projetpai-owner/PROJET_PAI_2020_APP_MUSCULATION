package fr.univ.lille.fil.mbprestservice.requestbody;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import fr.univ.lille.fil.mbprestservice.enumeration.Sexe;

public class CreateUserBody {
	
	private String nom;
	private String prenom;
	private Date bornDate;
	private String email;
	private String password;
	@Enumerated(EnumType.STRING)
	private Sexe sexe;
	private int sid;
	private String adresse;

	public String getNom() {
		return nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public Date getBornDate() {
		return bornDate;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public Sexe getSexe() {
		return sexe;
	}
	public int getSid() {
		return sid;
	}
	public String getAdresse() {
		return adresse;
	}
	
	
	

	
	
}
