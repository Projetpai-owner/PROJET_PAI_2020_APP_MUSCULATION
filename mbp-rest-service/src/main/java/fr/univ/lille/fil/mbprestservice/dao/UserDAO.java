package fr.univ.lille.fil.mbprestservice.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import fr.univ.lille.fil.mbprestservice.entity.User;
import fr.univ.lille.fil.mbprestservice.repository.UserRepository;


@Service
public class UserDAO {

	@Autowired
	UserRepository userRepository;
	
	//save a user
	public User save(User user) {
		return userRepository.save(user);
	}
	
	//list user
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	//find by id
	public Optional<User> findById(String id) {
		return userRepository.findById(id);
	}
	
	//delete 
	public void delete(User user) {
		userRepository.delete(user);
	}
}
