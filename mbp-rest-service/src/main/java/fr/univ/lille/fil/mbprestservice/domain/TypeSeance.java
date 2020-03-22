package fr.univ.lille.fil.mbprestservice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "type_seance")
public class TypeSeance {

	@Id
	@Column(name = "id_seance")
	private int idSeance;

	private String libelle;

	public int getIdSeance() {
		return idSeance;
	}

	public void setIdSeance(int idSeance) {
		this.idSeance = idSeance;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}
