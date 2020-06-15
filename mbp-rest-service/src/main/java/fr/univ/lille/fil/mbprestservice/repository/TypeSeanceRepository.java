package fr.univ.lille.fil.mbprestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.univ.lille.fil.mbprestservice.entity.TypeSeance;

/**
 * Repository qui permet de d'interagir avec la table TypeSeance
 * @author Théo
 *
 */
public interface TypeSeanceRepository extends JpaRepository<TypeSeance, Integer> {

}
