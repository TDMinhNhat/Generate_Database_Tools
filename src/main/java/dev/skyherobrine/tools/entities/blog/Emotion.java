package dev.skyherobrine.tools.entities.blog;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "Emotion")
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor
public class Emotion {

    @Id
    @Column(name = "emotion_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "emotion_name", length = 100, nullable = false, unique = true)
    @NonNull
    private String emotionName;

    @Column(nullable = false)
    @NonNull
    private Boolean status;
}
