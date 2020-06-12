package fr.univ.lille.fil.mbprestservice.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
	
	public Advert findByAid(int aid) {
		return advertRepository.findByAid(aid);
	}
	
	public void delete(int id) {
		 advertRepository.deleteById(id);
	}
	
	public int updateAdvert(@Valid Advert advert, int aid) {
		return advertRepository.updateAdvert(advert.getDescription(), advert.getNiveauPratique(),advert.getDureeSeance(),advert.getNom() , advert.getDateSeance(), aid);
		 
	}
	
	public List<ListAdvertItemDTO> findAllAdverts(){
		List<Advert> ads = advertRepository.findAll();
		List<ListAdvertItemDTO> res = new ArrayList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		for(Advert ad : ads) {
			ListAdvertItemDTO tmp = new ListAdvertItemDTO();
			tmp.setAid(ad.getAid());
			tmp.setDate(formatter.format(ad.getDateSeance()));
			tmp.setDescription(ad.getDescription());
			tmp.setDuree(""+ad.getDureeSeance());
			tmp.setNom(ad.getNom());
			tmp.setNiveau(ad.getNiveauPratique().name());
			tmp.setType(ad.getIdSeance().getLibelle());
			res.add(tmp);
		}
		return res;
	}
}
