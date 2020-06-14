package fr.univ.lille.fil.mbprestservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import fr.univ.lille.fil.mbprestservice.entity.composite.ParticipationId;

/**
 * Entité qui définie le type Participe
 * @author Théo
 *
 */
@Entity
@Table(name = "participe")
@IdClass(ParticipationId.class)
public class Participe {

	@Id
	private int idUser;
	@Id
	private int idAnnonce;
	
	public Participe() {
		
	}
	
	public Participe(int idUser, int idAnnonce) {
		this.idUser = idUser;
		this.idAnnonce = idAnnonce;
	}
	
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdAnnonce() {
		return idAnnonce;
	}
	public void setIdAnnonce(int idAnnonce) {
		this.idAnnonce = idAnnonce;
	}
	
	
}
