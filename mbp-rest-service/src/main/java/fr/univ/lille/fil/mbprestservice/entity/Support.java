package fr.univ.lille.fil.mbprestservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entité qui permet de définir le type Support
 * représentant un ticket de support technique 
 * avec l'username du créateur du ticket
 * ainsi que l'objet et la description de la demande
 * @author Rem
 *
 */
@Entity
@Table(name = "support")
public class Support {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int suid;
	private String username;
	private String object;
	private String description;
	
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getSuid() {
		return suid;
	}
	public void setSuid(int suid) {
		this.suid = suid;
	}
	
}
