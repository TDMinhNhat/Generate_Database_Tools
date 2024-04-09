package dev.skyherobrine.tools.entities.blog;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity @Table(name = "Comment")
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor
public class Comment {

	@Id
	@Column(name = "comment_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "comment_content", length = 500, nullable = false)
	@NonNull
	private String content;

	@ManyToOne
	@JoinColumn(name = "blog_id", nullable = false)
	@NonNull
	private Blog blog;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@NonNull
	private User user;

	@Column(name = "date_commented", nullable = false)
	@NonNull
	private LocalDateTime dateCommented;

	@ManyToOne
	@JoinColumn(name = "comment_parent")
	private Comment comment;
	
}
