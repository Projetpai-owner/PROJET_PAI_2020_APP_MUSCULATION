package fr.univ.lille.fil.mbprestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.univ.lille.fil.mbprestservice.entity.Support;

public interface SupportRepository extends JpaRepository<Support, Integer>{
	
	@Modifying(clearAutomatically=true)
	@Query("DELETE FROM Support s WHERE s.suid = :suid")
	public int deleteTicket(@Param("suid") int suid);
	
	public Support findBySuid(int suid);

}
