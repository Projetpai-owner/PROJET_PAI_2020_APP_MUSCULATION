package fr.univ.lille.fil.mbprestservice.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entité qui définie le type d'une séance
 * @author Théo
 *
 */
@Entity
@Table(name = "type_seance")
public class TypeSeance implements Serializable{

	private static final long serialVersionUID = 3019234408725889354L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
