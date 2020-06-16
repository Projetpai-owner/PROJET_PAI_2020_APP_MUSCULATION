package fr.univ.lille.fil.mbprestservice.entity.composite;

/**
 * Clé composite qui définie l'id d'une propriété d'annonce
 * @author Théo
 */
import java.io.Serializable;

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
