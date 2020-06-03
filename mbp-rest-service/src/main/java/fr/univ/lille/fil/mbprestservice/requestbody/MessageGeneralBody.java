package fr.univ.lille.fil.mbprestservice.requestbody;

/**
 * Objet représentant une requête pour l'envoie d'un message général
 * @author Clément
 *
 */
public class MessageGeneralBody {
	
	/**
	 * Objet du message
	 */
	private String objet;
	/**
	 * Contenu du message
	 */
	private String message;
	
	public String getObjet() {
		return objet;
	}
	public void setObjet(String objet) {
		this.objet = objet;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
