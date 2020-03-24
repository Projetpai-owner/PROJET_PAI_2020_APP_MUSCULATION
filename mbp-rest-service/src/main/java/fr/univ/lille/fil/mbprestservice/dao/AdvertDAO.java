package fr.univ.lille.fil.mbprestservice.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.univ.lille.fil.mbprestservice.entity.Advert;
import fr.univ.lille.fil.mbprestservice.entity.TypeSeance;
import fr.univ.lille.fil.mbprestservice.repository.AdvertRepository;
import fr.univ.lille.fil.mbprestservice.requestbody.CreateAdvertBody;

@Service
public class AdvertDAO {

	@Autowired
	AdvertRepository advertRepository;

	@Autowired
	TypeSeanceDAO typeSeanceDAO;

	public Advert save(Advert advert) {
		return advertRepository.save(advert);
	}

	public List<Advert> findAll() {
		return advertRepository.findAll();
	}

	public Advert mapFromDto(CreateAdvertBody body) {
		Advert adv = new Advert();
		adv.setDescription(body.getDescription());
		adv.setDateSeance(body.getDateSeance());
		adv.setDureeSeance(body.getDureeSeance());
		adv.setNom(body.getNom());
		adv.setNiveauPratique(body.getNiveauPratique());
		TypeSeance advertTypeSeance = typeSeanceDAO.findById(body.getIdSeance()).orElse(null);
		adv.setIdSeance(advertTypeSeance);
		return adv;
	}
}
