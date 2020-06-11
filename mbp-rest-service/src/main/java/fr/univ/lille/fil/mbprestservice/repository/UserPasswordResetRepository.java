package fr.univ.lille.fil.mbprestservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.univ.lille.fil.mbprestservice.entity.UserPasswordReset;
/**
 * Classe lié à la table contenant le token pour réinitialiser un mot de passe
 * @author Anthony Bliecq
 *
 */
public interface UserPasswordResetRepository extends JpaRepository<UserPasswordReset, String> {
	 Optional<UserPasswordReset> findByUsername(String username);
	 Optional<UserPasswordReset> findByToken(String token);

}
