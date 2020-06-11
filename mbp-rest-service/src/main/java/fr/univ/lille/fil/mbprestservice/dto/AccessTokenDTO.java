package fr.univ.lille.fil.mbprestservice.dto;
/**
 * Classe de mapping pour l'access token
 * @author Anthony Bliecq
 *
 */
public class AccessTokenDTO {
	private String jwt;
	

    public AccessTokenDTO(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
