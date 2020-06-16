package fr.univ.lille.fil.mbprestservice.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univ.lille.fil.mbprestservice.dto.ListAdvertItemDTO;
import fr.univ.lille.fil.mbprestservice.entity.Advert;
import fr.univ.lille.fil.mbprestservice.repository.AdvertRepository;

/**
 * Classe de service qui offre des méthodes d'interaction avec les annonces
 * @author Théo
 *
 */
@Service
public class AdvertService {

	@Autowired
	AdvertRepository advertRepository;

	
	/**
	 * Permet de faire appel au repository pour sauvegarder une annonce
	 * @param advert
	 * @return l'annonce sauvegardée
	 */
	public Advert save(Advert advert) {
		return advertRepository.save(advert);
	}
	
	/**
	 * Permet d'obtenir la liste de toutes les annonces
	 * @return la liste des annonces
	 */
	public List<Advert> findAll() {
		return advertRepository.findAll();
	}
	
	/**
	 * Permet de faire appel au repository pour retrouver une annonce via son id
	 * @param aid
	 * @return l'annonce recherchée
	 */
	public Advert findByAid(int aid) {
		return advertRepository.findByAid(aid);
	}
	
	/**
	 * Renvoi la liste des annonces dont l'id passé en paramètre n'est pas celui du propriétaire de ces dernières.
	 * @param id_proprietaire
	 * @return la liste des annonces dont l'id passé en paramètre n'est pas celui du propriétaire de ces dernières
	 */
	public List<ListAdvertItemDTO> findAllWhereNotProprietaire(int idProprietaire){
		return advertRepository.findAllWhereNotProprietaire(idProprietaire);
	}
	
	/**
	 * Renvoi la liste des annonces dont l'id passé en paramètre est celui du propriétaire de ces dernières.
	 * @param id_proprietaire
	 * @return la liste des annonces dont l'id passé en paramètre est celui du propriétaire de ces dernières
	 */
	public List<ListAdvertItemDTO> findAllWhereProprietaire(int idProprietaire){
		return advertRepository.findAllWhereProprietaire(idProprietaire);
	}
	
	/**
	 * Permet de supprimer une annonce en base de donnée.
	 * @param id
	 */
	public void delete(int id) {
		 advertRepository.deleteById(id);
	}
	
	/**
	 * Permet de mettre à jour une annonce en base de donnée
	 * @param advert
	 * @param aid
	 * @return l'id de l'annonce supprimée
	 */
	public int updateAdvert(@Valid Advert advert, int aid) {
		return advertRepository.updateAdvert(advert.getDescription(), advert.getNiveauPratique(),advert.getDureeSeance(),advert.getNom() , advert.getDateSeance(), aid);
		 
	}
	
	/**
	 * Renvoi la liste de toutes les annonces au type ListAdvertItemDTO
	 * @return la liste de toutes les annonces au type ListAdvertItemDTO
	 */
	public List<ListAdvertItemDTO> findAllAdverts(){
		List<Advert> ads = advertRepository.findAll();
		List<ListAdvertItemDTO> res = new ArrayList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		for(Advert ad : ads) {
			ListAdvertItemDTO tmp = new ListAdvertItemDTO();
			tmp.setAid(ad.getAid());
			tmp.setDateSeance(formatter.format(ad.getDateSeance()));
			tmp.setDescription(ad.getDescription());
			tmp.setDuree(""+ad.getDureeSeance());
			tmp.setNom(ad.getNom());
			tmp.setNiveau(ad.getNiveauPratique().name());
			tmp.setType(ad.getIdSeance().getLibelle());
			res.add(tmp);
		}
		return res;
	}
	
	/**
	 * Retourne la liste des annonces avec des informations en plus que sur la méthode 'findAllAvert' classique.
	 * On a en plus : le type de séance, le niveau de la séance, la salle de sport.
	 * @return La liste des annonces et leurs informations
	 */
	public List<ListAdvertItemDTO> findAllAdvertsWithInfos(){
		return advertRepository.getAdvertsWithInfo();
	}
}
