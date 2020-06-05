package fr.univ.lille.fil.mbprestservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univ.lille.fil.mbprestservice.dto.AmiDTO;
import fr.univ.lille.fil.mbprestservice.entity.Ami;
import fr.univ.lille.fil.mbprestservice.entity.AmiPk;
import fr.univ.lille.fil.mbprestservice.entity.User;
import fr.univ.lille.fil.mbprestservice.repository.AmiRepository;
import fr.univ.lille.fil.mbprestservice.repository.UserRepository;

@Service
public class AmiService {

	
	@Autowired
	private AmiRepository amiRepository;
	@Autowired
	private UserRepository userRepository;
	
	
	public List<AmiDTO> getAllAmi(int pid){
		User me=userRepository.findByPid(pid);
		List<AmiDTO> amis=new ArrayList<>();
		List<Ami> response=amiRepository.findByPidun(me);
		
		for(Ami a:response) {
			AmiDTO ami=new AmiDTO();
			User monAmi=a.getPiddeux();
			ami.setPid(monAmi.getPid());
			ami.setNom(monAmi.getNom());
			ami.setPrenom(monAmi.getPrenom());
			ami.setUsername(monAmi.getUsername());
			
			amis.add(ami);
		}
		
		return amis;
	}


	/*public void addAmi(int pidun,int piddeux) {
		User userun=userRepository.findByPid(pidun);
		User userdeux=userRepository.findByPid(piddeux);
		Ami ami=new Ami();
		ami.setPidun(userun);
		ami.setPiddeux(userdeux);
		amiRepository.save(ami);
		
	}
	*/
	
	public void delete(int pidun,int piddeux) {
		User userun=userRepository.findByPid(pidun);
		User userdeux=userRepository.findByPid(piddeux);
		AmiPk ami=new AmiPk(userun.getPid(), userdeux.getPid());
		amiRepository.deleteById(ami);
		
	}
	
}
