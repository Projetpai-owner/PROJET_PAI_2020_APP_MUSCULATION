package fr.univ.lille.fil.mbprestservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.univ.lille.fil.mbprestservice.entity.Participe;

public interface ParticipationRepository extends JpaRepository<Participe, String>{

}
