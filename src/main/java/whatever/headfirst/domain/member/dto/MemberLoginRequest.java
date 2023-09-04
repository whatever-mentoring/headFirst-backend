package whatever.headfirst.domain.member.dto;

import jakarta.validation.Valid;
import lombok.Getter;

@Getter
public class MemberLoginRequest {

    @Valid
    private Long uuid;
}
