package com.hansol.hrm.service.staff.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class StaffDto {

	private Long id;
	private String name;
	private String phone_number;
	private String type;
	private Long taskId;
	private Long positionId;

	public StaffDto() {
	}

	@Builder
	public StaffDto(Long id, String name, String phone_number, String type, Long taskId, Long positionId) {
		this.id = id;
		this.name = name;
		this.phone_number = phone_number;
		this.type = type;
		this.taskId = taskId;
		this.positionId = positionId;
	}

	public void setName(String name) {
		this.name = name;
	}
}
