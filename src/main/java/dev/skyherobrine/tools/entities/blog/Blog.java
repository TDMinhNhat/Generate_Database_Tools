package dev.skyherobrine.tools.entities.blog;

import java.time.LocalDateTime;
import dev.skyherobrine.tools.entities.blog.enums.BlogStatus;

public class Blog {
	
	private String blogId;
	private String title;
	private String content;
	private User user;
	private LocalDateTime dateCreated;
	private BlogCategory category;
	private BlogType type;
	private BlogStatus status;
	private LocalDateTime dateModifier;
	
}
