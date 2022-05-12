package com.hansol.hrm.service.staff.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
public class StaffEntity {

	private Long id;
	private String name;
	private String phone_number;
	private String type;
	private Long taskId;
	private Long positionId;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public StaffEntity() {
	}

	@Builder
	public StaffEntity(Long id, String name, String phone_number, String type, Long taskId, Long positionId,
		LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.name = name;
		this.phone_number = phone_number;
		this.type = type;
		this.taskId = taskId;
		this.positionId = positionId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}
