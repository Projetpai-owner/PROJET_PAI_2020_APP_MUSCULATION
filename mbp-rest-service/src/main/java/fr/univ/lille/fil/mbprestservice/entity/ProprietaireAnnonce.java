package fr.univ.lille.fil.mbprestservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import fr.univ.lille.fil.mbprestservice.entity.composite.ProprietaireAnnonceId;

@Entity
@Table(name = "proprietaire_annonce")
@IdClass(ProprietaireAnnonceId.class)
public class ProprietaireAnnonce {

	@Id
	@Column(name = "pid_proprietaire")
	private int pidProprietaire;
	@Id
	@Column(name = "aid")
	private int aid;
	
	public ProprietaireAnnonce() {
		
	}
	
	public ProprietaireAnnonce(int pidProprietaire, int aid) {
		this.pidProprietaire = pidProprietaire;
		this.aid = aid;
	}

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
