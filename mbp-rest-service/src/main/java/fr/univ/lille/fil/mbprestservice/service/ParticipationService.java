package fr.univ.lille.fil.mbprestservice.service;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<Integer> findUsersByParticipation(int aid){
		List<Participe> tmp =  this.repo.findAllByIdAnnonce(aid);
		List<Integer> tmp2 = new ArrayList<>();
		for(Participe p : tmp) {
			tmp2.add(p.getIdUser());
		}
		return tmp2;
	}

}
