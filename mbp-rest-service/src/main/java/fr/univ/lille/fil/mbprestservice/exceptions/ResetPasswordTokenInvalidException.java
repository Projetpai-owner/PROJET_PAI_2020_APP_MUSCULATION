package fr.univ.lille.fil.mbprestservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *  Classe représentant une exception lorsque le token lié au mot de passe à réinitialiser est invalide
 * @author Anthony Bliecq
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Le token de réinitialisation du mot de passe est invalide")
public class ResetPasswordTokenInvalidException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
