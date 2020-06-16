package fr.univ.lille.fil.mbprestservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Classe représentant une exception lorsqu'un problème est survenue lors de l'envoi d'un mail
 * @author Rem
 *
 */
@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED, reason="Erreur lors de l'envoi du mail")
public class EmailSendingException extends RuntimeException {

}
