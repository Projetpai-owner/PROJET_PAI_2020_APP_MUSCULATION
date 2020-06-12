package fr.univ.lille.fil.mbprestservice.service;
/**
 * Service pour les actions sur les bannis
 */
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univ.lille.fil.mbprestservice.entity.Banni;
import fr.univ.lille.fil.mbprestservice.repository.BanniRepository;


@Service
public class BanniService {

	@Autowired
	BanniRepository banniRepository;
	
	/**
	 * Sauvegarde un banni en base
	 * @param banni le banni à sauvegarder
	 * @return le banni sauvegarder
	 */
	public Banni save(Banni banni) {
		return banniRepository.save(banni);
	}
	
	/**
	 * Récupère la liste de tous les bannis 
	 * @return List<Banni>
	 */
	public List<Banni> findAll(){
		return banniRepository.findAll();
	}
	
	/**
	 * Trouve un banni en base en fonction de son id
	 * @param id l'id du panni à trouver
	 * @return un objet Optional<Bani>
	 */
	public Optional<Banni> findById(String id) {
		return banniRepository.findById(id);
	}
	
	/**
	 * Supprime un Banni en base
	 * @param banni le panni qui doit être supprimer en base
	 */
	public void delete(Banni banni) {
		banniRepository.delete(banni);
	}
}
