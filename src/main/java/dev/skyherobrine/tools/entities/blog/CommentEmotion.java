package dev.skyherobrine.tools.entities.blog;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity @Table(name = "CommentEmotion")
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor
public class CommentEmotion {
    @Id
    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false)
    @NonNull
    private Comment comment;

    @Id
    @ManyToOne
    @JoinColumn(name = "emotion_id", nullable = false)
    @NonNull
    private Emotion emotion;

    @Column(name = "date_emoted", nullable = false)
    @NonNull
    private LocalDateTime dateEmoted;

    @Column(nullable = false)
    @NonNull
    private Boolean exist;
}
