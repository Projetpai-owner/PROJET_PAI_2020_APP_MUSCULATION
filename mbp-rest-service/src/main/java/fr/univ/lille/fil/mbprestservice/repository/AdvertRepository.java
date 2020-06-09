package fr.univ.lille.fil.mbprestservice.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.univ.lille.fil.mbprestservice.dto.ListAdvertItemDTO;
import fr.univ.lille.fil.mbprestservice.entity.Advert;
import fr.univ.lille.fil.mbprestservice.enumeration.Niveau;

public interface AdvertRepository extends JpaRepository<Advert, Integer>{
	
	
	@Modifying(clearAutomatically=true)
	@Query("UPDATE Advert u SET u.description = :description, u.niveauPratique = :niveauPratique, u.dureeSeance = :dureeSeance,u.nom = :nom, u.dateSeance = :dateSeance WHERE u.aid = :aid")
	public int updateAdvert(@Param("description") String description, @Param("niveauPratique") Niveau niveauPratique, 
			@Param("dureeSeance") int dureeSeance, @Param("nom") String nom, @Param("dateSeance") Date dateSeance, @Param("aid") int aid);
	
	public Advert findByAid(int aid);
	
	@Query("SELECT new fr.univ.lille.fil.mbprestservice.dto.ListAdvertItemDTO(a.aid,a.nom,a.description,a.dateSeance,a.dureeSeance,a.idSeance,a.niveauPratique,u.sexe,u.sid) "+
			"FROM fr.univ.lille.fil.mbprestservice.entity.Advert a JOIN fr.univ.lille.fil.mbprestservice.entity.ProprietaireAnnonce p ON a.aid = p.aid " + 
			"JOIN fr.univ.lille.fil.mbprestservice.entity.User u ON p.pidProprietaire = u.pid ")
	public List<ListAdvertItemDTO> getAdvertsWithInfo();
}
