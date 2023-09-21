package whatever.headfirst.domain.member.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import whatever.headfirst.domain.common.BaseEntity;
import whatever.headfirst.domain.heart.entity.Heart;
import whatever.headfirst.domain.member.dto.MemberResponse;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long uuid;
    private String email;
    private String nickname;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Heart> hearts;

    private Member(Long uuid, String email, String nickname) {
        this.uuid = uuid;
        this.email = email;
        this.nickname = nickname;
    }

    public static Member from(Long uuid, String email, String nickname) {
        return new Member(uuid, email, nickname);
    }

    public MemberResponse toDto() {
        return MemberResponse.from(getUuid(), getNickname(), getEmail());
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }
}
