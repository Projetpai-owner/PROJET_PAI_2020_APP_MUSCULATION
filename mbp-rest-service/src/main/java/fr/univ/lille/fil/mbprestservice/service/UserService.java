package fr.univ.lille.fil.mbprestservice.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.univ.lille.fil.mbprestservice.dto.AccessTokenDTO;
import fr.univ.lille.fil.mbprestservice.dto.AuthenticationResponseDTO;
import fr.univ.lille.fil.mbprestservice.entity.Ami;
import fr.univ.lille.fil.mbprestservice.entity.User;
import fr.univ.lille.fil.mbprestservice.entity.UserPasswordReset;
import fr.univ.lille.fil.mbprestservice.exceptions.ResetPasswordTokenExpiratedException;
import fr.univ.lille.fil.mbprestservice.exceptions.ResetPasswordTokenInvalidException;
import fr.univ.lille.fil.mbprestservice.repository.AmiRepository;
import fr.univ.lille.fil.mbprestservice.repository.UserPasswordResetRepository;
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
	@Autowired
	private UserPasswordResetRepository userPasswordResetRepository;
	
	public User save(@Valid User user) {
		return userRepository.save(user);
	}
	
	public int updateUser (@Valid User user) {
		return userRepository.updateUser(user.getPassword(), user.getSid(), user.getAdresse(), user.getUsername());
	}
	
	public int cancelUserAccount(String username) {
		return userRepository.cancelUserAccount(username);
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
	
	public void logoutUserByUsername(String username){
		userRefreshTokenRepository.findById(username)
		.ifPresent(userRefreshTokenRepository::delete);
	}

	public AuthenticationResponseDTO login(String username) {
		final UserDetails userDetails = loadUserByUsername(username);
		final String accessToken = jwtTokenUtil.generateToken(userDetails.getUsername());
		final String refreshToken = jwtTokenUtil.createRefreshToken(userDetails);
		
		return new AuthenticationResponseDTO(accessToken,refreshToken,""+((User)userDetails).getPid(),
				((User)userDetails).getPrenom(),userDetails.getAuthorities());

	}

	public String createResetPasswordToken(String username) {
		//On supprime le précédent token
        userPasswordResetRepository.findByUsername(username).ifPresent(userPasswordResetRepository::delete);
		
		String token = RandomStringUtils.randomAlphanumeric(128);
        UserPasswordReset passwordReset=new UserPasswordReset();
        passwordReset.setToken(token);
        passwordReset.setUsername(username);
        //valable 24h
        Date expiration =new Date(System.currentTimeMillis()+1000*60*60*24);
        passwordReset.setExpiration(expiration);
        userPasswordResetRepository.save(passwordReset);
        return token;

	}

	public UserPasswordReset loadUserWithPasswordResetToken(String token){
		UserPasswordReset passwordReset= userPasswordResetRepository.findByToken(token).orElse(null);
		if(passwordReset==null) {
			throw new ResetPasswordTokenInvalidException();
		}
		if(passwordReset.getExpiration().before(new Date())) {
			throw new ResetPasswordTokenExpiratedException();
		}
		return passwordReset;
	}

	public void resetPassword(User user, String password) {
		user.setPassword(password);
		userRepository.changePassword(password, user.getUsername());
        userPasswordResetRepository.findByUsername(user.getUsername()).ifPresent(userPasswordResetRepository::delete);
		
	}
	
	
	public int deleteUser(String username){
		return userRepository.deleteUserByUsername(username);
	}
	
	public List<User> getUserNotInFriendList(int pidun){
		return userRepository.findByPidNotInList(pidun);
	}
	

}
