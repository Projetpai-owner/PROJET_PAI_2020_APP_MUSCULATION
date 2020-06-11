package fr.univ.lille.fil.mbprestservice.requestbody;

/**
 * Classe représentant le mapping d'un body d'une requête post pour l'authentification
 * @author Anthony Bliecq
 *
 */
public class AuthenticationRequest {
	private String username;
	private String password;
	
	public AuthenticationRequest() {
		// TODO Auto-generated constructor stub
	}

	public AuthenticationRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
