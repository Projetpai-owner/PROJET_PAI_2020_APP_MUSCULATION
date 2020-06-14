package fr.univ.lille.fil.mbprestservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.univ.lille.fil.mbprestservice.entity.Participe;

public interface ParticipationRepository extends JpaRepository<Participe, String>{
	
	public List<Participe> findAllByIdAnnonce(int idAnnonce);

}
