package whatever.headfirst.domain.member.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class MemberLoginRequest {

    @NotEmpty
    private String token;
}
