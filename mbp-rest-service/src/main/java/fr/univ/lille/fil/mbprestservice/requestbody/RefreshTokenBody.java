package fr.univ.lille.fil.mbprestservice.requestbody;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;

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
