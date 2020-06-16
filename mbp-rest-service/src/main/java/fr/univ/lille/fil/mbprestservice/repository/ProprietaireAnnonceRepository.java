package fr.univ.lille.fil.mbprestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.univ.lille.fil.mbprestservice.entity.ProprietaireAnnonce;

/**
 * Classe représentant un repository qui permet trouver le propriétaire d'une annonce 
 * et de supprimer la relation propriétaire - annonce lors de la suppression d'une annonce
 * @author Rem
 *
 */
public interface ProprietaireAnnonceRepository extends JpaRepository<ProprietaireAnnonce, Integer>{
	
	Integer deleteByAid(int aid);
	
	ProprietaireAnnonce findByAid(int aid);

}
