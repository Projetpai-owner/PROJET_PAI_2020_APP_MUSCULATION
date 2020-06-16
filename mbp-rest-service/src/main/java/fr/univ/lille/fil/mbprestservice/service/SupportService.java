package fr.univ.lille.fil.mbprestservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univ.lille.fil.mbprestservice.entity.Support;
import fr.univ.lille.fil.mbprestservice.repository.SupportRepository;

/**
 * Classe de Service qui permet d'intéragir avec la table gérant les tickets de support technique
 * @author Rem
 *
 */
@Service
public class SupportService {

	@Autowired 
	SupportRepository supportRepository;
	
	/**
	 * Permet de créer un ticket de support technique
	 * @param support
	 * @return l'objet Support créé
	 */
	public Support createTicket (Support support) {
		return supportRepository.save(support);
	}
	
	/**
	 * Permet de récupérer tous les tickets de support présents en base
	 * @return la liste de tous les tickets de support
	 */
	public List<Support> getAllTickets(){
		return supportRepository.findAll();
	}
	
	/**
	 * Permet de supprimer un ticket de support en fonction de son id donné en paramètre
	 * @param suid
	 */
	public void deleteTicket(int suid){
		supportRepository.deleteById(suid);
	} 
	
	/**
	 * Permet de récupérer un ticket de suppport en fonction de son id donné en paramètre
	 * @param suid
	 * @return l'objet Support trouvé
	 */
	public Support getTicketById(int suid) {
		return this.supportRepository.findBySuid(suid);
	}
	
}
