package whatever.headfirst.domain.heart.controller.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import whatever.headfirst.domain.member.domain.Member;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HeartResponse {

    private Long id;

    private Long owner;

    private Long storyId;
}
