package fr.univ.lille.fil.mbprestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.univ.lille.fil.mbprestservice.entity.Salle;

/**
 * Classe représentant un repository qui permet de récupérer les salles de sport
 * @author Rem
 *
 */
public interface SalleRepository extends JpaRepository<Salle, Integer>{

}
