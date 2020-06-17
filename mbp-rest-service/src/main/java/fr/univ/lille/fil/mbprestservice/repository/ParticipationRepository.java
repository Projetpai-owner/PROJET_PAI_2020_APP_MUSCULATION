package fr.univ.lille.fil.mbprestservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.univ.lille.fil.mbprestservice.entity.Participe;

/**
 * Repository qui permet de d'interagir avec la table Participe
 * @author Théo
 *
 */
public interface ParticipationRepository extends JpaRepository<Participe, String>{
	
	public List<Participe> findAllByIdAnnonce(int idAnnonce);
	
	public List<Participe> findAllByIdUser(int idUser);
	
	public List<Participe> deleteByIdAnnonce(int idAnnonce);

}
