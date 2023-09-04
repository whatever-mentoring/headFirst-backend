package whatever.headfirst.domain.member.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponse {

    private Long uuid;
    private String nickname;
    private String email;

    private MemberResponse(Long uuid, String nickname, String email) {
        this.uuid = uuid;
        this.nickname = nickname;
        this.email = email;
    }

    public static MemberResponse from(Long uuid, String nickname, String email) {
        return new MemberResponse(uuid, nickname, email);
    }
}
