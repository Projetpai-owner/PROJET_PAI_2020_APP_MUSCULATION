package fr.univ.lille.fil.mbprestservice.requestbody;
/**
 * Objet représentant un banni envoyé par la WebApp 
 *
 */
public class BannirUserBody {
	private String email;

	public String getEmail() {
		return email;
	}

	public void setUsername(String username) {
		this.email = username;
	}
	
	
}