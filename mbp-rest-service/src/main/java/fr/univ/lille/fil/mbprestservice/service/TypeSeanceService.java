package fr.univ.lille.fil.mbprestservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univ.lille.fil.mbprestservice.entity.TypeSeance;
import fr.univ.lille.fil.mbprestservice.repository.TypeSeanceRepository;

/**
 * Classe de service qui offre des méthodes d'interaction avec les types de séance
 * @author Théo
 *
 */
@Service
public class TypeSeanceService {
	
	@Autowired
	TypeSeanceRepository typeSeanceRepository;
	
	/**
	 * Méthode permettant de récupérer un type de séance grâce à son id
	 * @param idTypeSeance
	 * @return
	 */
	public Optional<TypeSeance> findById(int idTypeSeance) {
		return typeSeanceRepository.findById(idTypeSeance);
	}
	
	/**
	 * Méthode faisant appel au repository pour retourner tous les types de séances
	 * @return
	 */
	public List<TypeSeance> findAll() {
		return typeSeanceRepository.findAll();
	}
	
}
	