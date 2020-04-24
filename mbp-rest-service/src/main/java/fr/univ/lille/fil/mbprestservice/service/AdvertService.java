package fr.univ.lille.fil.mbprestservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univ.lille.fil.mbprestservice.dto.ListAdvertItemDTO;
import fr.univ.lille.fil.mbprestservice.entity.Advert;
import fr.univ.lille.fil.mbprestservice.repository.AdvertRepository;

@Service
public class AdvertService {

	@Autowired
	AdvertRepository advertRepository;

	

	public Advert save(Advert advert) {
		return advertRepository.save(advert);
	}

	public List<Advert> findAll() {
		return advertRepository.findAll();
	}
	
	public void delete(int id) {
		 advertRepository.deleteById(id);
	}
	
	public List<ListAdvertItemDTO> findAllAdverts(){
		List<Advert> ads = advertRepository.findAll();
		List<ListAdvertItemDTO> res = new ArrayList<>();
		for(Advert ad : ads) {
			ListAdvertItemDTO tmp = new ListAdvertItemDTO();
			tmp.setAid(ad.getAid());
			tmp.setDate(ad.getDateSeance().toString());
			tmp.setDescription(ad.getDescription());
			tmp.setDuree(""+ad.getDureeSeance());
			tmp.setNomAnnonce(ad.getNom());
			res.add(tmp);
		}
		return res;
	}
}
