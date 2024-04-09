package dev.skyherobrine.tools.entities.blog;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity @Table(name = "BlogCategory")
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor
public class BlogCategory {

	@Id
	@Column(name = "category_id", length = 100)
	@NonNull
	private String categoryId;

	@Column(name = "category_name", length = 100, nullable = false)
	@NonNull
	private String categoryName;

	@Column(length = 200)
	private String description;
	
}
