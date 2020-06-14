package fr.univ.lille.fil.mbprestservice.dto;

/**
 * Objet représentant l'ajout d'un participant sur une annonce
 * @author Théo
 *
 */
public class AddParticipationDTO {
	
	private int uid;
	private int aid;
	
	public AddParticipationDTO(int uid, int aid) {
		this.uid = uid;
		this.aid = aid;
	}
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	
	

}
