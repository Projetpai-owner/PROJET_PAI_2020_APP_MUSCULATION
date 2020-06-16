package fr.univ.lille.fil.mbprestservice.requestbody;

/**
 * Objet utlisé par la requête de création ou de suppression de ticket de support
 * @author Rem
 *
 */
public class SupportBody {
	
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
	

}
