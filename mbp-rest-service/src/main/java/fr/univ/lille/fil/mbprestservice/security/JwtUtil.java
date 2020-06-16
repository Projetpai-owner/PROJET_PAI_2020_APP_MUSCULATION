package fr.univ.lille.fil.mbprestservice.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import fr.univ.lille.fil.mbprestservice.entity.UserRefreshToken;
import fr.univ.lille.fil.mbprestservice.repository.UserRefreshTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
/**
 * Classe permettant la gestion d'un token
 * @author Anthony Bliecq
 *
 */
@Service
public class JwtUtil {
	
	@Autowired
	private UserRefreshTokenRepository userRefreshTokenRepository;
	
	private static final String SECRETKEY="secret";
	
	/**
	 * Récupère le username correspondant au token passé
	 * @param token
	 * @return
	 */
	public String extractUsername(String token) {
		return extractClaim(token,Claims::getSubject);
	}

	/**
	 * Récupère la date d'expiration lié au token passé
	 * @param token
	 * @return
	 */
	public Date extractExpiration(String token) {
		return extractClaim(token,Claims::getExpiration);
	}
	
	
	public <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
		final Claims claims=extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRETKEY).parseClaimsJws(token).getBody();
	}
	
	/**
	 * Vérifie si le token est valide
	 * @param token
	 * @return
	 */
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	/**
	 * Génère un token
	 * @param userName
	 * @return
	 */
	public String generateToken(String userName) {
		Map<String,Object> claims=new HashMap<>();
		return createToken(claims,userName);
		
	}
	
	/**
	 * Crée un refresh-token
	 * @param userDetails
	 * @return
	 */
	public String createRefreshToken(UserDetails userDetails) {
        String token = RandomStringUtils.randomAlphanumeric(128);
        UserRefreshToken userRefreshToken=new UserRefreshToken();
        userRefreshToken.setToken(token);
        userRefreshToken.setUsername(userDetails.getUsername());
        userRefreshTokenRepository.save(userRefreshToken);
        return token;
	}

	/**
	 * Crée un token grâce au username
	 * @param claims
	 * @param username
	 * @return
	 */
	private String createToken(Map<String, Object> claims, String username) {
		return Jwts.builder().setClaims(claims)
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*15))
				.signWith(SignatureAlgorithm.HS256, SECRETKEY).compact();
	}

	
	public boolean validateToken(String token,UserDetails userDetails) {
		final String username=extractUsername(token);
		return (username.equals(userDetails.getUsername())&&!isTokenExpired(token));
	}
	
	

}
