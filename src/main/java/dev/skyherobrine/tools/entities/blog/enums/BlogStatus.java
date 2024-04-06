package dev.skyherobrine.tools.entities.blog.enums;

public enum BlogStatus {
	
	OPENED(1), CLOSED(0), BLOCKED(-1);
	
	private final int value;
	
	BlogStatus(int value) {
		this.value = value;
	}
	
}
