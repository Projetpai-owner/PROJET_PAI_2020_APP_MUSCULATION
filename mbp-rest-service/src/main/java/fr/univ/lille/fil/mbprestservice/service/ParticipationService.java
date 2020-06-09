package fr.univ.lille.fil.mbprestservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univ.lille.fil.mbprestservice.entity.Participe;
import fr.univ.lille.fil.mbprestservice.repository.ParticipationRepository;

@Service
public class ParticipationService {
	
	@Autowired
	ParticipationRepository repo;
	
	public void register(int uid, int aid) {
		repo.save(new Participe(uid, aid));
	}

}
