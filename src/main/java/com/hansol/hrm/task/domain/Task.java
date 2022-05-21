package com.hansol.hrm.task.domain;

import com.hansol.hrm.task.dto.TaskDto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Task {

	private Long id;
	private String name;
	private String code;

	public Task() {
	}

	@Builder
	public Task(Long id, String name, String code) {
		this.id = id;
		this.name = name;
		this.code = code;
	}

	public static Task convertDtoToEntity(TaskDto taskDto) {
		return Task.builder()
			.name(taskDto.getName())
			.code(taskDto.getCode())
			.build();
	}
}
