package com.hansol.hrm.service.task.dto;

import com.hansol.hrm.service.task.domain.Task;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TaskDto {

	private Long id;
	private String name;
	private String code;

	public TaskDto() {
	}

	@Builder
	public TaskDto(Long id, String name, String code) {
		this.id = id;
		this.name = name;
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static TaskDto convertEntityToDto(Task task) {
		return TaskDto.builder()
			.id(task.getId())
			.name(task.getName())
			.code(task.getCode())
			.build();
	}
}
