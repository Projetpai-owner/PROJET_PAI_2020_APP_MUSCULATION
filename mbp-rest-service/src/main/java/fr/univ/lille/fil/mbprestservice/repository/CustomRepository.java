package fr.univ.lille.fil.mbprestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.univ.lille.fil.mbprestservice.domain.Advert;

public interface CustomRepository extends JpaRepository<Advert, Long>{

}
