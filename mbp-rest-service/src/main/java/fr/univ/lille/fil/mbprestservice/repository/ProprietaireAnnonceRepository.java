package fr.univ.lille.fil.mbprestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.univ.lille.fil.mbprestservice.entity.ProprietaireAnnonce;

public interface ProprietaireAnnonceRepository extends JpaRepository<ProprietaireAnnonce, Integer>{
	
	Integer deleteByAid(int aid);
	
	ProprietaireAnnonce findByAid(int aid);

}
