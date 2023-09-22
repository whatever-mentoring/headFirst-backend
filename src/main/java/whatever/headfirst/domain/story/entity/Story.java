package whatever.headfirst.domain.story.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import whatever.headfirst.domain.comment.domain.Comment;
import whatever.headfirst.domain.common.BaseEntity;
import whatever.headfirst.domain.member.domain.Member;
import whatever.headfirst.domain.story.entity.enums.StoryStatus;


import java.util.List;

@Data
@Entity
@Getter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Story extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "story_id")
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 100, nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String keyword;

    @Column(nullable = false)
    private String latitude;

    @Column(nullable = false)
    private String longitude;

    @JsonBackReference
    @OneToMany(mappedBy = "story", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderBy("id asc")
    private List<Comment> comments;

    @Column(nullable = true)
    private Integer heartCount;

    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private StoryStatus status;

    public Story() {
        this.heartCount = 0;
    }

}
