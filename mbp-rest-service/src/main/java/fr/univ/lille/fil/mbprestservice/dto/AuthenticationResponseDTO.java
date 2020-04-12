package fr.univ.lille.fil.mbprestservice.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class AuthenticationResponseDTO {
	private final String accessToken;
	private final String refreshToken;
	private final String userId;
	private final String role;
	private final String prenom;
	
	public AuthenticationResponseDTO(String accessToken,String refreshToken, String userId, String prenom,Collection<? extends GrantedAuthority> collection) {
		super();
		this.accessToken = accessToken;
		this.refreshToken=refreshToken;
		this.userId = userId;
		this.prenom=prenom;
		this.role=collection.iterator().next().getAuthority();
	}

	
	public String getPrenom() {
		return prenom;
	}




	public String getAccessToken() {
		return accessToken;
	}


	public String getRefreshToken() {
		return refreshToken;
	}


	public String getUserId() {
		return userId;
	}

	public String getRole() {
		return role;
	}

}
