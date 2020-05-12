package fr.univ.lille.fil.mbprestservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="Le token de réinitialisation du mot de passe est invalide")
public class ResetPasswordTokenExpiratedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
