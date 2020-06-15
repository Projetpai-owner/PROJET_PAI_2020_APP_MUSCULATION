package fr.univ.lille.fil.mbprestservice.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.univ.lille.fil.mbprestservice.entity.Banni;
import fr.univ.lille.fil.mbprestservice.requestbody.BannirUserBody;
import fr.univ.lille.fil.mbprestservice.service.BanniService;
import fr.univ.lille.fil.mbprestservice.service.MailService;
import fr.univ.lille.fil.mbprestservice.service.UserService;
/**
 * Classe controller qui reçoit les requêtes REST liées à la gestion des bannissement d'utilisateurs
 *
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BanniController {
	@Autowired
	private BanniService banniService;
	@Autowired
	private UserService userService;

	/**
	 * Enregistre un utilisateur comme banni : on ajoute l'utilisateur à la liste des bannis, on le supprimre de la liste des utilisateurs
	 * 	puis on le notifie par mail.
	 * @param bannirUserBody informations de l'utilisateur à bannir envoyées par la WebApp
	 * @return l'objet Banni enregistré en base
	 */
	@Transactional
	@PostMapping("/addBanni")
	public Banni createBanni(@Valid @RequestBody BannirUserBody bannirUserBody) {
		Banni banni = this.mapFromDTO(bannirUserBody);
		// Supprime le user en base
		this.userService.logoutUserByUsername(banni.getEmail());
		this.userService.deleteUser(banni.getEmail());

		String object = "Votre compte MyBodyPartner a été banni";
		String message = "Bonjour,\n\nL'équipe MyBodyPartner vous informe que votre compte lié à l'adresse "
				+ banni.getEmail() + " a été banni.\n" + "Si vous souhaitez plus d'informations contacter le support.";
		this.sendMail(banni, object, message);
		return banniService.save(banni);
	}

	/**
	 * Récupère tous les bannis en base
	 * @return List<Banni> les utilisateurs bannis
	 */
	@GetMapping("/getBannedUsers")
	public List<Banni> getAllBanni() {
		return banniService.findAll();
	}

	/**
	 * Récupère un banni en fonction de son email
	 * @param email l'email du banni à retrouver
	 * @return le banni correspondant à l'email passé en paramètre
	 */
	@GetMapping("/banni/{email}")
	public Optional<Banni> findById(@PathVariable String email) {
		return banniService.findById(email);
	}
	/**
	 * Transforme un objet "BannirUserBody" envoyé par la WebApp en objet Banni utilisable par l'API REST
	 * @param bannirUserBody l'objet envoyé par la WebApp
	 * @return un objet Banni 
	 */
	private Banni mapFromDTO(BannirUserBody bannirUserBody) {
		Banni banni = new Banni();
		banni.setEmail(bannirUserBody.getEmail());
		return banni;
	}
	
	/**
	 * Envoie un mail à un utilisateur à partir d'un objet Banni
	 * @param banni le banni à qui envoyé un mail
	 * @param object l'objet du mail
	 * @param message le mail à envoyé
	 */
	private void sendMail(Banni banni, String object, String message) {
		new Thread(new MailService(banni.getEmail(), object, message)).start();
	}

}
