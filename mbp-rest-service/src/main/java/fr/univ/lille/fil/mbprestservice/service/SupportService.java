package fr.univ.lille.fil.mbprestservice.service;

import java.util.List;

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
	
	public List<Support> getAllTickets(){
		return supportRepository.findAll();
	}
	
	public void deleteTicket(int suid){
		supportRepository.deleteById(suid);
	} 
	
	public Support getTicketById(int suid) {
		return this.supportRepository.findBySuid(suid);
	}
	
}
