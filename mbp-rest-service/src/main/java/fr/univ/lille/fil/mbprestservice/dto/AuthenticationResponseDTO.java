package fr.univ.lille.fil.mbprestservice.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class AuthenticationResponseDTO {
	private final String jwt;
	private final String userId;
	private final String role;
	private final String prenom;
	
	public AuthenticationResponseDTO(String jwt, String userId, String prenom,Collection<? extends GrantedAuthority> collection) {
		super();
		this.jwt = jwt;
		this.userId = userId;
		this.prenom=prenom;
		this.role=collection.iterator().next().getAuthority();
	}

	
	public String getPrenom() {
		return prenom;
	}


	public String getJwt() {
		return jwt;
	}

	public String getUserId() {
		return userId;
	}

	public String getRole() {
		return role;
	}

}
