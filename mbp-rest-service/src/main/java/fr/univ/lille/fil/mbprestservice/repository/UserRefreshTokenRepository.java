package fr.univ.lille.fil.mbprestservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.univ.lille.fil.mbprestservice.entity.UserRefreshToken;

public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken, String> {
	 Optional<UserRefreshToken> findByToken(String token);
}
