package dev.skyherobrine.tools.entities.blog;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity @Table(name = "BlogType")
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor
public class BlogType {

	@Id
	@Column(name = "type_id", length = 100)
	@NonNull
	private String typeId;

	@Column(name = "type_name", length = 100, nullable = false)
	@NonNull
	private String typeName;

	@Column(length = 200)
	private String description;

	@Column(name = "type_status", nullable = false)
	@NonNull
	private Boolean status;
	
}
