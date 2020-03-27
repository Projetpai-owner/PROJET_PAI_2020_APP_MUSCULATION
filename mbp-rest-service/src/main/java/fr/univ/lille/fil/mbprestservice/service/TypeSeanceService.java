package fr.univ.lille.fil.mbprestservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univ.lille.fil.mbprestservice.entity.TypeSeance;
import fr.univ.lille.fil.mbprestservice.repository.TypeSeanceRepository;

@Service
public class TypeSeanceService {
	
	@Autowired
	TypeSeanceRepository typeSeanceRepository;
	
	public Optional<TypeSeance> findById(int idTypeSeance) {
		return typeSeanceRepository.findById(idTypeSeance);
	}
	
	public List<TypeSeance> findAll() {
		return typeSeanceRepository.findAll();
	}
	
}
	