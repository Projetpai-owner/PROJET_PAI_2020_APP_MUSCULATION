package fr.univ.lille.fil.mbprestservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univ.lille.fil.mbprestservice.entity.Support;
import fr.univ.lille.fil.mbprestservice.repository.SupportRepository;

@Service
public class SupportService {

	@Autowired 
	SupportRepository supportRepository;
	
	public Support createTicket (Support support) {
		return supportRepository.save(support);
	}
	
}
