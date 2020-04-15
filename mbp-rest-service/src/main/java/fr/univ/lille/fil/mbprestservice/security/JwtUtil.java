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

@Service
public class JwtUtil {
	
	@Autowired
	private UserRefreshTokenRepository userRefreshTokenRepository;
	
	private final String SECRET_KEY="secret";
	
	public String extractUsername(String token) {
		return extractClaim(token,Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token,Claims::getExpiration);
	}
	
	
	public <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
		// TODO Auto-generated method stub
		final Claims claims=extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		// TODO Auto-generated method stub
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
	
	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public String generateToken(String userName) {
		Map<String,Object> claims=new HashMap<>();
		return createToken(claims,userName);
		
	}
	
	public String createRefreshToken(UserDetails userDetails) {
        String token = RandomStringUtils.randomAlphanumeric(128);
        UserRefreshToken userRefreshToken=new UserRefreshToken();
        userRefreshToken.setToken(token);
        userRefreshToken.setUsername(userDetails.getUsername());
        userRefreshTokenRepository.save(userRefreshToken);
        return token;
	}

	private String createToken(Map<String, Object> claims, String username) {
		// TODO Auto-generated method stub
		return Jwts.builder().setClaims(claims)
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*1))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

	public boolean validateToken(String token,UserDetails userDetails) {
		final String username=extractUsername(token);
		return (username.equals(userDetails.getUsername())&&!isTokenExpired(token));
	}
	
	

}
