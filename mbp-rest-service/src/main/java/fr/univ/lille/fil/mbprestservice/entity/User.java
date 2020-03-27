package fr.univ.lille.fil.mbprestservice.entity;

import java.util.Date;

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
@Table(name = "personne")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pid;
	private String nom;
	private String prenom;
	@Column(name="date_de_naissance")
	private Date bornDate;
	private String sexe;
	private String email;
	private String password;

	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "sid")
	private Gym sid;

	private String adresse;


	public Date getBornDate() {
		return bornDate;
	}

	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public Gym getSid() {
		return sid;
	}

	public void setSid(Gym sid) {
		this.sid = sid;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	@Override
	public String toString() {
		return "User [pid=" + pid + ", nom=" + nom + ", prenom=" + prenom + ", bornDate=" + bornDate + ", sexe=" + sexe
				+ ", email=" + email + ", password=" + password + ", sid=" + sid + ", adresse=" + adresse + "]";
	}



}
