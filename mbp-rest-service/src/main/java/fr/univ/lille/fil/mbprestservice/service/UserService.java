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
/**
 * Classe service permettant de récupérer les données nécessaires liés aux utilisateurs
 * @author Shadow
 *
 */
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
	
	/**
	 * Enregistre un utilisateur
	 * @param user
	 * @return
	 */
	public User save(@Valid User user) {
		return userRepository.save(user);
	}
	
	/**
	 * Met à jour un utilisateur
	 * @param user
	 * @return
	 */
	public int updateUser (@Valid User user) {
		return userRepository.updateUser(user.getPassword(), user.getSid(), user.getAdresse(), user.getUsername());
	}
	
	/**
	 * Supprime un utilisateur
	 * @param username
	 * @return
	 */
	public int cancelUserAccount(String username) {
		return userRepository.cancelUserAccount(username);
	}
	
	
	/**
	 * Liste tous les utilisateurs enregistrés
	 * @return
	 */
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	/**
	 * Récupère un utilisateur grâce à son identifiant
	 * @param userId
	 * @return
	 */
	public User findUserById(String userId) {
		return userRepository.findByPid(Integer.valueOf(userId));
	}
	
	/**
	 * Récupère un utilisateur grâce à son username (email)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}

	/**
	 * Regénère un nouveau access token à partir du refresh token
	 * @param refreshToken
	 * @return
	 */
	public Optional<AccessTokenDTO> refreshAccessToken(String refreshToken) {
		 return userRefreshTokenRepository.findByToken(refreshToken)
	                .map(userRefreshToken -> new AccessTokenDTO(
	                        jwtTokenUtil.generateToken(userRefreshToken.getUsername())
	                ));
	}

	/**
	 * Supprime l'access token et le refresh d'un token d'un utilisateur
	 * @param refreshToken
	 */
	public void logoutUser(String refreshToken) {
		userRefreshTokenRepository.findByToken(refreshToken)
        .ifPresent(userRefreshTokenRepository::delete);		
	}
	
	/**
	 * Supprime l'access token et le refresh d'un token d'un utilisateur
	 * @param username
	 */
	public void logoutUserByUsername(String username){
		userRefreshTokenRepository.findById(username)
		.ifPresent(userRefreshTokenRepository::delete);
	}

	/**
	 * Crée un refresh token et un access token pour un utilisateur
	 * @param username
	 * @return
	 */
	public AuthenticationResponseDTO login(String username) {
		final UserDetails userDetails = loadUserByUsername(username);
		final String accessToken = jwtTokenUtil.generateToken(userDetails.getUsername());
		final String refreshToken = jwtTokenUtil.createRefreshToken(userDetails);
		
		return new AuthenticationResponseDTO(accessToken,refreshToken,""+((User)userDetails).getPid(),
				((User)userDetails).getPrenom(),userDetails.getAuthorities());

	}

	/**
	 * Crée un token pour la réinitialisation d'un mot de passe
	 * @param username
	 * @return
	 */
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

	/**
	 * Récupère un utilisateur avec le token pour la réinitialisation d'un mot de passe
	 * @param token
	 * @return
	 */
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

	/**
	 * Modifie le mot de passe d'un utilisateur
	 * @param user
	 * @param password
	 */
	public void resetPassword(User user, String password) {
		user.setPassword(password);
		userRepository.changePassword(password, user.getUsername());
        userPasswordResetRepository.findByUsername(user.getUsername()).ifPresent(userPasswordResetRepository::delete);
		
	}
	
	
	/**
	 * Supprime un utilisateur via son username
	 * @param username
	 * @return
	 */
	public int deleteUser(String username){
		return userRepository.deleteUserByUsername(username);
	}
	
	/**
	 * Récupère tous les utilisateurs qui ne sont pas dans les contacts de l'utilisateur donné (l'utilisateur lui-même n'est pas retourné)
	 * @param pidun
	 * @return
	 */
	public List<User> getUserNotInFriendList(int pidun){
		return userRepository.findByPidNotInList(pidun);
	}
	

}
