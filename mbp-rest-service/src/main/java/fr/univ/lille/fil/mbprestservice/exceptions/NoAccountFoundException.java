package fr.univ.lille.fil.mbprestservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe représentant une exception lorsque un utilisateur qui n'a pas de compte tente de se connecter à l'application
 * @author Rem
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Utilisateur inconnu")
public class NoAccountFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
