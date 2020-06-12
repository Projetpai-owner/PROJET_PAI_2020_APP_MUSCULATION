package fr.univ.lille.fil.mbprestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.univ.lille.fil.mbprestservice.entity.Banni;
/**
 * Repository  pour les actions en BDD de la table Banni
 *
 */
public interface BanniRepository extends JpaRepository<Banni, String>{

}
