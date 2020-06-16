package fr.univ.lille.fil.mbprestservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univ.lille.fil.mbprestservice.entity.Salle;
import fr.univ.lille.fil.mbprestservice.repository.SalleRepository;

/**
 * Classe de Service qui permet d'intéragir avec la table contenant les salles de sport
 * @author Rem
 *
 */
@Service
public class SalleService {

	@Autowired
	private SalleRepository salleRepository;
	
	/**
	 * Permet de trouver une salle de sport en fonction de l'id de la salle donné en paramètre
	 * @param id
	 * @return
	 */
	public Optional<Salle> findById(int id) {
		return salleRepository.findById(id);
	}
	
	/**
	 * Permet de récupérer toutes les salles de sport présentes en base
	 * @return
	 */
	public List<Salle> findAll() {
		return salleRepository.findAll();
	}
	
}
