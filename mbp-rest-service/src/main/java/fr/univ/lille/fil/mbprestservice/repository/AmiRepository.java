package fr.univ.lille.fil.mbprestservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.univ.lille.fil.mbprestservice.entity.Ami;
import fr.univ.lille.fil.mbprestservice.entity.AmiPk;
import fr.univ.lille.fil.mbprestservice.entity.User;

/**
 * Classe lié à la table de l'association contact-utilisateur
 * @author Anthony Bliecq
 *
 */
public interface AmiRepository extends JpaRepository<Ami, AmiPk>{
	
	public List<Ami> findByPidun(User pidun);


}
