package fr.univ.lille.fil.mbprestservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * Classe représentant une exception lorsque qu'un email est déjà existant dans la table utilisateur
 * @author Anthony Bliecq
 *
 */
@ResponseStatus(value = HttpStatus.CONFLICT, reason="Cet email est déjà utilisé par un autre compte")
public class EmailAlreadyExistException extends RuntimeException {

}
