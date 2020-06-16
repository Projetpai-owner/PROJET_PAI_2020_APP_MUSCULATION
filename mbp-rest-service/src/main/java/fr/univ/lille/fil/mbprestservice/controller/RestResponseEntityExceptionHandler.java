package fr.univ.lille.fil.mbprestservice.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
/**
 * classe traitant des exceptions spécifiques données
 * @author Anthony Bliecq
 *
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	/**
	 * Récupère l'exception lié à l'interdiction d'accès à une requête pour retourner un message personnalisé
	 * @param ex
	 * @param request
	 * @return
	 */
	  @ExceptionHandler({ AccessDeniedException.class })
	    public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
	        return new ResponseEntity<>(
	          "Access denied for "+request.getDescription(false), new HttpHeaders(), HttpStatus.FORBIDDEN);
	    }
}
