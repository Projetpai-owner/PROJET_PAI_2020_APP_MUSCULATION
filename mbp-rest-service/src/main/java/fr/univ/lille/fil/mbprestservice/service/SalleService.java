package fr.univ.lille.fil.mbprestservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univ.lille.fil.mbprestservice.entity.Salle;
import fr.univ.lille.fil.mbprestservice.repository.SalleRepository;

@Service
public class SalleService {

	@Autowired
	private SalleRepository salleRepository;
	
	public Optional<Salle> findById(int id) {
		return salleRepository.findById(id);
	}
	
}
