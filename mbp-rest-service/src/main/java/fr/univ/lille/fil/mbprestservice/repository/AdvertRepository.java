package fr.univ.lille.fil.mbprestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fr.univ.lille.fil.mbprestservice.entity.Advert;
import fr.univ.lille.fil.mbprestservice.entity.User;

public interface AdvertRepository extends JpaRepository<Advert, Integer>{
	
	
	@Modifying(clearAutomatically=true)
	@Query("UPDATE Advert u SET u.description = :description, u.niveauPratique = :niveau_pratique, u.dureeSeance = :duree_seance,u.nom = :nom, u.dateSeance = :date_seance WHERE u.aid = :aid")
	public Advert updateAdvert();
	
	public Advert findByAid(int aid);

}
