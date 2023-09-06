package whatever.headfirst.domain.member.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MemberRequest {

    @NotNull
    private Long uuid;
    @NotEmpty
    private String email;
    @NotEmpty
    private String nickname;
}
