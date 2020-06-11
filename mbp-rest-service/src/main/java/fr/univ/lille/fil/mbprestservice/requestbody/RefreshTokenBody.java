package fr.univ.lille.fil.mbprestservice.requestbody;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
/**
 * Classe représentant le mapping d'un body lors d'une requête post pour le rafraichissement d'un access token
 * @author Anthony Bliecq
 *
 */
public class RefreshTokenBody {
    @NotBlank
    private final String refreshToken;

    @JsonCreator
    public RefreshTokenBody(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
