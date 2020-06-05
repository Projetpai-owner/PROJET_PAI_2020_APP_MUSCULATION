package fr.univ.lille.fil.mbprestservice.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.univ.lille.fil.mbprestservice.dto.AmiDTO;
import fr.univ.lille.fil.mbprestservice.service.AmiService;

@CrossOrigin
@RestController
public class AmiController {
	@Autowired
	private AmiService amiService;
	
	@GetMapping("/getAllFriends/{pid}")
	public List<AmiDTO> getAllAmis(@PathVariable(value="pid")final int pid) {
		
		return amiService.getAllAmi(pid);
	}
	
/*	@PostMapping("/addFriend/{pidun}")
	public ResponseEntity<String> addAmi(@PathVariable(value="pidun")int pidun,@RequestBody int piddeux){
		amiService.addAmi(pidun,piddeux);
		return new ResponseEntity<>("{ \"message\": \"friend added successfully\" }", HttpStatus.OK);

	}
	*/
	
	@DeleteMapping("/deleteFriend/{userId}/{pid}")
	public ResponseEntity<String> deleteFriend(@PathVariable("userId") int userId ,@PathVariable("pid") int pid) {
		System.out.println("coucou");
		System.out.println(pid);
		this.amiService.delete(userId,pid);
		
		return new ResponseEntity<>("{ \"message\": \"Deleted friend with success\" }", HttpStatus.OK);
	}
	
}

