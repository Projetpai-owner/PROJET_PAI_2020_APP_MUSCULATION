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
	
	public List<Integer> findAdvertByParticipation(int uid){
		List<Participe> tmp = this.repo.findAllByIdUser(uid);
		List<Integer> tmp2 = new ArrayList<>();
		for(Participe p : tmp) {
			System.out.println(p.getIdAnnonce());
			tmp2.add(p.getIdAnnonce());
		}
		return tmp2;
	}
	
	public void deleteParticipation(int aid, int pid) {
		Participe p = new Participe();
		p.setIdAnnonce(aid);
		p.setIdUser(pid);
		repo.delete(p);
	}

}
