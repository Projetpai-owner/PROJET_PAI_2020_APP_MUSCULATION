package fr.univ.lille.fil.mbprestservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univ.lille.fil.mbprestservice.entity.ProprietaireAnnonce;
import fr.univ.lille.fil.mbprestservice.repository.ProprietaireAnnonceRepository;

/**
 * Classe de Service qui permet d'intéragir avec la table reliant les utilisateurs aux annonces qu'ils ont créés
 * @author Rem
 *
 */
@Service
public class ProprietaireAnnonceService {
	
	@Autowired
	ProprietaireAnnonceRepository repo;
	
	/**
	 * Permet d'insérer la référence de l'annonce avec la référence de l'utilisateur qui l'a créée
	 * @param uid, l'id de l'utilisateur créant l'annonce
	 * @param aid, l'id de l'annonce créée
	 */
	public void register(int uid, int aid) {
		repo.save(new ProprietaireAnnonce(uid, aid));
	}
	
	/**
	 * Permet de supprimer le couple utilisateur - annonce avec l'id de l'annonce donné en paramètre 
	 * @param aid
	 */
	public void deleteByAid(int aid) {
		this.repo.deleteByAid(aid);
	}
	
	/**
	 * Permet de retrouver le propriétaire d'une annonce en fonction de l'id de l'annonce donné en paramètre
	 * @param aid
	 * @return
	 */
	public ProprietaireAnnonce findProprioByAid(int aid) {
		return this.repo.findByAid(aid);
	}


}
