package fr.univ.lille.fil.mbprestservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Classe représentant une exception lorsque un refresh-token n'est plus valide
 * @author Anthony Bliecq
 *
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN,reason="JWT refresh Token invalid")
public class InvalidRefreshTokenException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidRefreshTokenException() {
		super();
	}

}
