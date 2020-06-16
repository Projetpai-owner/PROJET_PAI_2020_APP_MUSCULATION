package fr.univ.lille.fil.mbprestservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.univ.lille.fil.mbprestservice.dto.ListAdvertItemDTO;
import fr.univ.lille.fil.mbprestservice.entity.Advert;
import fr.univ.lille.fil.mbprestservice.entity.ProprietaireAnnonce;
import fr.univ.lille.fil.mbprestservice.entity.TypeSeance;
import fr.univ.lille.fil.mbprestservice.requestbody.CreateAdvertBody;
import fr.univ.lille.fil.mbprestservice.requestbody.EditAdvertBody;
import fr.univ.lille.fil.mbprestservice.service.AdvertService;
import fr.univ.lille.fil.mbprestservice.service.ProprietaireAnnonceService;
import fr.univ.lille.fil.mbprestservice.service.TypeSeanceService;

/**
 * Controller qui permettant d'intéragir avec les annonces
 * @author Théo
 *
 */
@RestController
@Secured(value = "ROLE_USER")
public class AdvertController {

	@Autowired
	AdvertService advertService;
	@Autowired
	TypeSeanceService typeSeanceService;
	@Autowired
	ProprietaireAnnonceService proprioService;
	
	/**
	 * Requête permettant la création d'une annonce via le endpoint /createAdvert et prenant un paramètre type CreateAdvertBody
	 * @param body
	 * @return l'annonce créée
	 */
	@CrossOrigin(origins="http://localhost:4200")
	@PostMapping("/createAdvert")
	public Advert createAdvert(@Valid @RequestBody CreateAdvertBody body) {
		Advert tmp =  this.advertService.save(mapFromDto(body));
		proprioService.register(body.getIdUser(), tmp.getAid());
		return tmp;
	}
	
	/**
	 * Requête permettant de supprimer une annonce via son id
	 * @param advertId
	 */
	@Transactional
	@DeleteMapping("/deleteAdvert/{aid}")
	public void deleteAdvert(@PathVariable("aid") int advertId) {
		this.proprioService.deleteByAid(advertId);
		this.advertService.delete(advertId);
	}
	
	/**
	 * Requête permettant de récupérer toutes les annonces
	 * @return la liste des annonces au format ListAdvertItemDTO
	 */
	@GetMapping("/getAllAdvertsItems")
	public List<ListAdvertItemDTO> getAllAdvertsItems(){
		return this.advertService.findAllAdvertsWithInfos();
	}
	
	/**
	 * Requête permettant de récuperer toutes les annonces dont l'id n'en est pas le proprietaire
	 * @param idProprietaire
	 * @return toutes les annonces dont l'id n'en est pas le proprietaire
	 */
	@GetMapping("/getAllWhereNotProprietaire/{idProprietaire}")
	public List<ListAdvertItemDTO> getAllWhereNotProprietaire(@PathVariable("idProprietaire") int idProprietaire){
		return this.advertService.findAllWhereNotProprietaire(idProprietaire);
	}
	
	/**
	 * Requête permettant de récuperer toutes les annonces dont l'id est le proprietaire
	 * @param idProprietaire
	 * @return toutes les annonces dont l'id est le proprietaire
	 */
	@GetMapping("/getAllWhereProprietaire/{idProprietaire}")
	public List<ListAdvertItemDTO> getAllWhereProprietaire(@PathVariable("idProprietaire") int idProprietaire){
		return this.advertService.findAllWhereProprietaire(idProprietaire);
	}
	
	/**
	 * Requête permettant de récupérer une annonce via son id
	 * @param id
	 * @return l'annonce supprimée
	 */
	@GetMapping("/getAdvertById/{id}")
	public Advert getAdvertById(@PathVariable(value="id") int id) {
		return this.advertService.findByAid(id);
	}
	
	/**
	 * Requête permettant de mettre à jour une annonce
	 * @param body
	 * @return l'annonce mis à jour
	 */
	@Transactional
	@PutMapping("/updateAdvert")
	public int updateAdvert(@Valid @RequestBody EditAdvertBody body) {
		Advert advert = mapFromDtoEdit(body);
		return advertService.updateAdvert(advert, body.getAid());
	}
	
	/**
	 * Requête permettant de récupérer le propriétaire d'une annonce
	 * @param aid
	 * @return le proprietaire d'une annonce
	 */
	@GetMapping("/getProprioByAid/{aid}")
	public ProprietaireAnnonce getProprioById(@PathVariable(value="aid") int aid) {
		return proprioService.findProprioByAid(aid);
	}

	/**
	 * Méthode permettant de convertir un CreateAdvertBody en objet de type Annonce
	 * @param body
	 * @return l'annonce convertie
	 */
	//a redefinir peut etre dans une couche business ou converter
	private Advert mapFromDto(CreateAdvertBody body) {
		Advert adv = new Advert();
		adv.setDescription(body.getDescription());
		adv.setDateSeance(body.getDateSeance());
		adv.setDureeSeance(body.getDureeSeance());
		adv.setNom(body.getNom());
		adv.setNiveauPratique(body.getNiveauPratique());
		TypeSeance advertTypeSeance = typeSeanceService.findById(body.getIdSeance()).orElse(null);
		adv.setIdSeance(advertTypeSeance);
		return adv;
	}
	
	/**
	 * Méthode permettant de transformer un objet de type EditAdvertBody en objet de type Annonce
	 * @param body
	 * @return l'annonce convertie
	 */
	private Advert mapFromDtoEdit(EditAdvertBody body) {
		Advert adv = new Advert();
		adv.setDescription(body.getDescription());
		adv.setDateSeance(body.getDateSeance());
		adv.setDureeSeance(body.getDureeSeance());
		adv.setNom(body.getNom());
		adv.setNiveauPratique(body.getNiveauPratique());
		TypeSeance advertTypeSeance = typeSeanceService.findById(body.getIdSeance()).orElse(null);
		adv.setIdSeance(advertTypeSeance);
		adv.setAid(body.getAid());
		return adv;
	}
	
}
