package whatever.headfirst.domain.token.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenResponse {

    private String accessToken;

    private TokenResponse(String token) {
        this.accessToken = token;
    }

    public static TokenResponse from(String token) {
        return new TokenResponse(token);
    }
}
