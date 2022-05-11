package com.hansol.hrm.service.base.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BaseEntity {

	private Long id;
	private String name;
	private String comment;

	public BaseEntity() {
	}

	@Builder
	public BaseEntity(Long id, String name, String comment) {
		this.id = id;
		this.name = name;
		this.comment = comment;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
