package fr.univ.lille.fil.mbprestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.univ.lille.fil.mbprestservice.entity.User;


public interface UserRepository extends JpaRepository<User, String> {

}
