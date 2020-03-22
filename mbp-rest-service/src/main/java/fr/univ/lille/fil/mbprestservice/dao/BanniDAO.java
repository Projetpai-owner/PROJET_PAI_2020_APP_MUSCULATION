package fr.univ.lille.fil.mbprestservice.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import fr.univ.lille.fil.mbprestservice.entity.Banni;
import fr.univ.lille.fil.mbprestservice.repository.BanniRepository;


@Service
public class BanniDAO {

	@Autowired
	BanniRepository banniRepository;
	
	//save a ban user
	public Banni save(Banni banni) {
		return banniRepository.save(banni);
	}
	
	//list banni
	public List<Banni> findAll(){
		return banniRepository.findAll();
	}
	
	//find by id
	public Optional<Banni> findById(String id) {
		return banniRepository.findById(id);
	}
	
	//delete 
	public void delete(Banni banni) {
		banniRepository.delete(banni);
	}
}
