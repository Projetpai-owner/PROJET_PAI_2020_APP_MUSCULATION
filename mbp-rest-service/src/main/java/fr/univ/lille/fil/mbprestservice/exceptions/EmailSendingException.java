package fr.univ.lille.fil.mbprestservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED, reason="Erreur lors de l'envoi du mail")
public class EmailSendingException extends RuntimeException {

}
