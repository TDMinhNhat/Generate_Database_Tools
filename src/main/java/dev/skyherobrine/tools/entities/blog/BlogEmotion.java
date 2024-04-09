package dev.skyherobrine.tools.entities.blog;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity @Table(name = "BlogEmotion")
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor
public class BlogEmotion {
    @Id
    @ManyToOne
    @JoinColumn(name = "blog_id", nullable = false)
    @NonNull
    private Blog blog;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NonNull
    private User user;

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
