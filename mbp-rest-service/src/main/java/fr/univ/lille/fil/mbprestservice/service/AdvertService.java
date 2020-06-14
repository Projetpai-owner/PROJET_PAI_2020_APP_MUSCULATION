package fr.univ.lille.fil.mbprestservice.service;

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
	
	public List<ListAdvertItemDTO> findAllWhereNotProprietaire(int id_proprietaire){
		List<Advert> ads = advertRepository.findAllWhereNotProprietaire(id_proprietaire);
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
	
	public List<ListAdvertItemDTO> findAllWhereProprietaire(int id_proprietaire){
		List<Advert> ads = advertRepository.findAllWhereProprietaire(id_proprietaire);
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
	
	public void delete(int id) {
		 advertRepository.deleteById(id);
	}
	
	public int updateAdvert(@Valid Advert advert, int aid) {
		return advertRepository.updateAdvert(advert.getDescription(), advert.getNiveauPratique(),advert.getDureeSeance(),advert.getNom() , advert.getDateSeance(), aid);
		 
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
