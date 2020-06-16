package fr.univ.lille.fil.mbprestservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Classe permettant d'intéragir avec la table contenant une relation contact et utilisateur
 * @author Anthony Bliecq
 *
 */
@Entity
@Table(name = "ami")
@IdClass(value=AmiPk.class)
public class Ami {
	
	 @Id
	 @ManyToOne
	 @JoinColumn(unique=false,name = "pidun")
	 private User pidun;
	 

	 @Id
	 @ManyToOne	
	 @JoinColumn(unique=false,name = "piddeux")
	 private User piddeux;


	 public Ami() {

	 }
	 
	public Ami(User pidun, User piddeux) {
		this.pidun = pidun;
		this.piddeux = piddeux;
	}


	public User getPidun() {
		return pidun;
	}


	public User getPiddeux() {
		return piddeux;
	}

	public void setPidun(User pidun) {
		this.pidun = pidun;
	}

	public void setPiddeux(User piddeux) {
		this.piddeux = piddeux;
	}
	 
	 
	 

	
	
}
