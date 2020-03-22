package fr.univ.lille.fil.mbprestservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TomcatController {
	
    @GetMapping("/")
    public String welcomeMessage() {
    	return "Bienvenue sur l'api rest MyBodyPartner";
    }
}