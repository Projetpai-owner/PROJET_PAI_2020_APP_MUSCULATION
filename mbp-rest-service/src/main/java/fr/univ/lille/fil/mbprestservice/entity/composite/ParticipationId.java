package fr.univ.lille.fil.mbprestservice.entity.composite;

import java.io.Serializable;

/**
 * Clé composite qui définie l'id d'une participation
 * @author Théo
 *
 */
public class ParticipationId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7451858661552621749L;
	private int idAnnonce;
	private int idUser;
	
	public ParticipationId() {
		
	}
	
	public ParticipationId(int idAnnonce, int idUser) {
		this.idAnnonce = idAnnonce;
		this.idUser = idUser;
	}
	
	
	public int getIdAnnonce() {
		return idAnnonce;
	}
	public void setIdAnnonce(int idAnnonce) {
		this.idAnnonce = idAnnonce;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	

}
