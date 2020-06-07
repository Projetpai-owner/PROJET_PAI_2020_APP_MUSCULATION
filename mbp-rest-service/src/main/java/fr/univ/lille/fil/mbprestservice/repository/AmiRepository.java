package fr.univ.lille.fil.mbprestservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.univ.lille.fil.mbprestservice.entity.Ami;
import fr.univ.lille.fil.mbprestservice.entity.AmiPk;
import fr.univ.lille.fil.mbprestservice.entity.User;

public interface AmiRepository extends JpaRepository<Ami, AmiPk>{
	
	public List<Ami> findByPidun(User pidun);


}
