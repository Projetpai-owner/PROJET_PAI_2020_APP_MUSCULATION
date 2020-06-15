package fr.univ.lille.fil.mbprestservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univ.lille.fil.mbprestservice.entity.ProprietaireAnnonce;
import fr.univ.lille.fil.mbprestservice.repository.ProprietaireAnnonceRepository;

@Service
public class ProprietaireAnnonceService {
	
	@Autowired
	ProprietaireAnnonceRepository repo;
	
	public void register(int uid, int aid) {
		repo.save(new ProprietaireAnnonce(uid, aid));
	}
	
	public void deleteByAid(int aid) {
		this.repo.deleteByAid(aid);
	}
	
	public ProprietaireAnnonce findProprioByAid(int aid) {
		return this.repo.findByAid(aid);
	}


}
