package fr.univ.lille.fil.mbprestservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.univ.lille.fil.mbprestservice.dao.BanniDAO;
import fr.univ.lille.fil.mbprestservice.entity.Banni;

@RestController
public class TomcatController {
	@Autowired
	BanniDAO banni;
	
    @GetMapping("/")
    public String welcomeMessage() {
    	return "Bienvenue sur l'api rest MyBodyPartner";
    }
}