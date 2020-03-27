package fr.univ.lille.fil.mbprestservice.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.univ.lille.fil.mbprestservice.dto.BanniDTO;
import fr.univ.lille.fil.mbprestservice.entity.Banni;
import fr.univ.lille.fil.mbprestservice.service.BanniService;

@RestController
public class BanniController {
	@Autowired
	private BanniService banniService;
	private ModelMapper modelMapper;

	public BanniController() {
		// TODO Auto-generated constructor stub
		modelMapper=new ModelMapper();
	}
		
	//save a banni
	@PostMapping("/banni")
	public Banni createBanni(@Valid @RequestBody BanniDTO banniDTO) {
		Banni banni=modelMapper.map(banniDTO, Banni.class);
		return banniService.save(banni);
		
	}
	
	//list all banni
	@GetMapping("/banni")
	public List<Banni> getAllBanni(){
		return banniService.findAll();
	}
	
	//find a specific banni
	@GetMapping("/banni/{email}")
	public Optional<Banni> findById(@PathVariable String email) {
		return banniService.findById(email);
	}
	
	
}
