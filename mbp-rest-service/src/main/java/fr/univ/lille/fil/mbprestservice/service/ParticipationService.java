package fr.univ.lille.fil.mbprestservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univ.lille.fil.mbprestservice.entity.Participe;
import fr.univ.lille.fil.mbprestservice.repository.ParticipationRepository;

/**
 * Classe de Service qui permet d'intéragir et de gérer la participation des annonces
 * @author Rem
 *
 */
@Service
public class ParticipationService {
	
	@Autowired
	ParticipationRepository repo;
	
	/**
	 * Insère une ligne dans la table de participation
	 * @param uid, l'id de l'utilisateur qui participe
	 * @param aid, l'id de l'annonce
	 */
	public void register(int uid, int aid) {
		repo.save(new Participe(uid, aid));
	}
	
	/**
	 * Méthode qui trouve tous les utilisateurs participants à l'annonce donnée en paramètre
	 * @param aid
	 * @return la liste des id user participant à cette annonce
	 */
	public List<Integer> findUsersByParticipation(int aid){
		List<Participe> tmp =  this.repo.findAllByIdAnnonce(aid);
		List<Integer> tmp2 = new ArrayList<>();
		for(Participe p : tmp) {
			tmp2.add(p.getIdUser());
		}
		return tmp2;
	}
	
	/**
	 * Méthode qui trouve toutes les annonces où participe l'user donné en paramètre
	 * @param uid
	 * @return la liste des id d'annonce où l'utilisateur participe
	 */
	public List<Integer> findAdvertByParticipation(int uid){
		List<Participe> tmp = this.repo.findAllByIdUser(uid);
		List<Integer> tmp2 = new ArrayList<>();
		for(Participe p : tmp) {
			tmp2.add(p.getIdAnnonce());
		}
		return tmp2;
	}
	
	/**
	 * Supprime la participation d'un utilisateur donné à une annonce donnée
	 * @param aid, l'id de l'annonce 
	 * @param pid, l'id de l'utilisateur
	 */
	public void deleteParticipation(int aid, int pid) {
		Participe p = new Participe();
		p.setIdAnnonce(aid);
		p.setIdUser(pid);
		repo.delete(p);
	}
	
	/**
	 * Supprime la participation de tous les utilisateurs pour une annonce donnée
	 * @param aid
	 */
	public void deleteParticipationByAid(int aid) {
		repo.deleteByIdAnnonce(aid);
	}

}
