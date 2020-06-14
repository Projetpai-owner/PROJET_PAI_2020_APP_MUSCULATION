package fr.univ.lille.fil.mbprestservice.entity.composite;

import java.io.Serializable;

import javax.persistence.Entity;
public class ProprietaireAnnonceId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pidProprietaire;
	private int aid;
	
	
	public int getPidProprietaire() {
		return pidProprietaire;
	}
	public void setPidProprietaire(int pidProprietaire) {
		this.pidProprietaire = pidProprietaire;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}

}
