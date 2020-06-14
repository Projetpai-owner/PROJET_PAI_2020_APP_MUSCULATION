package fr.univ.lille.fil.mbprestservice.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entite permettant d'interagir avec la table permettant de contenir les token liés au reset d'un mot de passe
 * @author Anthony Bliecq
 *
 */
@Entity
@Table(name = "user_password_reset")
public class UserPasswordReset {
	
	@Id
	private String username;
	
    @Column(nullable = false)
	private String token;
    
   
    private Date expiration;
	
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
	
	
	
	
	
}
