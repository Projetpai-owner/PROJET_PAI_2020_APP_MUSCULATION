package fr.univ.lille.fil.mbprestservice.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.univ.lille.fil.mbprestservice.dto.AccessTokenDTO;
import fr.univ.lille.fil.mbprestservice.dto.AuthenticationResponseDTO;
import fr.univ.lille.fil.mbprestservice.entity.User;
import fr.univ.lille.fil.mbprestservice.repository.UserRefreshTokenRepository;
import fr.univ.lille.fil.mbprestservice.repository.UserRepository;
import fr.univ.lille.fil.mbprestservice.security.JwtUtil;

@Service
public class UserService implements UserDetailsService{
	@Autowired
	private JwtUtil jwtTokenUtil;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRefreshTokenRepository userRefreshTokenRepository;
	
	public User save(@Valid User user) {
		return userRepository.save(user);
	}
	
	public int updateUser (@Valid User user) {
		return userRepository.updateUser(user.getPassword(), user.getSid(), user.getAdresse(), user.getUsername());
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findUserById(String userId) {
		return userRepository.findByPid(Integer.valueOf(userId));
	}
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}

	public Optional<AccessTokenDTO> refreshAccessToken(String refreshToken) {
		 return userRefreshTokenRepository.findByToken(refreshToken)
	                .map(userRefreshToken -> new AccessTokenDTO(
	                        jwtTokenUtil.generateToken(userRefreshToken.getUsername())
	                ));
	}

	public void logoutUser(String refreshToken) {
		userRefreshTokenRepository.findByToken(refreshToken)
        .ifPresent(userRefreshTokenRepository::delete);		
	}

	public AuthenticationResponseDTO login(String username) {
		final UserDetails userDetails = loadUserByUsername(username);
		final String accessToken = jwtTokenUtil.generateToken(userDetails.getUsername());
		final String refreshToken = jwtTokenUtil.createRefreshToken(userDetails);
		
		return new AuthenticationResponseDTO(accessToken,refreshToken,""+((User)userDetails).getPid(),
				((User)userDetails).getPrenom(),userDetails.getAuthorities());

	}
	
	


}
