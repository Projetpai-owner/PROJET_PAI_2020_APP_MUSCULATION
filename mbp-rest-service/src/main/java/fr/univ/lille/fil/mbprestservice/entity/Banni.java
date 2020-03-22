package fr.univ.lille.fil.mbprestservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "banni")
public class Banni {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Banni [email=" + email + "]";
	}
	
	

}
