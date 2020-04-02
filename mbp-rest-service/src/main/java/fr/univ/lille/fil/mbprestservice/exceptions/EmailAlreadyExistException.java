package fr.univ.lille.fil.mbprestservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason="Cet email est déjà utilisé par un autre compte")
public class EmailAlreadyExistException extends RuntimeException {

}
