package fr.univ.lille.fil.mbprestservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.univ.lille.fil.mbprestservice.entity.UserPasswordReset;
import fr.univ.lille.fil.mbprestservice.entity.UserRefreshToken;

public interface UserPasswordResetRepository extends JpaRepository<UserPasswordReset, String> {
	 Optional<UserPasswordReset> findByUsername(String username);
	 Optional<UserPasswordReset> findByToken(String token);

}
