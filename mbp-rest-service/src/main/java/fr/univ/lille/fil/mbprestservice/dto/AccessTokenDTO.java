package fr.univ.lille.fil.mbprestservice.dto;

public class AccessTokenDTO {
	private String jwt;
	

    public AccessTokenDTO(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
