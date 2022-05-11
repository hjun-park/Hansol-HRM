package com.hansol.hrm.service.base.dto;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BaseDto {

	private String name;
	private String comment;

	public BaseDto() {
	}

	@Builder
	public BaseDto(String name, String comment) {
		this.name = name;
		this.comment = comment;
	}
}
