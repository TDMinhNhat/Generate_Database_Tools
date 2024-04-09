package dev.skyherobrine.tools.entities.blog;

import java.time.LocalDateTime;
import dev.skyherobrine.tools.entities.blog.enums.BlogStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "Blog")
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor
public class Blog {

	@Id
	@Column(name = "blog_id", length = 100)
	@NonNull
	private String blogId;

	@Column(name = "blog_title", length = 100, nullable = false)
	@NonNull
	private String title;

	@Column(name = "blog_content", length = 2000, nullable = false)
	@NonNull
	private String content;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@NonNull
	private User user;

	@Column(name = "date_created", nullable = false)
	@NonNull
	private LocalDateTime dateCreated;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	@NonNull
	private BlogCategory category;

	@ManyToOne
	@JoinColumn(name = "type_id", nullable = false)
	@NonNull
	private BlogType type;

	@Enumerated(EnumType.ORDINAL)
	@Column(nullable = false)
	@NonNull
	private BlogStatus status;

	@Column(name = "date_modifier", nullable = false)
	@NonNull
	private LocalDateTime dateModifier;
	
}
