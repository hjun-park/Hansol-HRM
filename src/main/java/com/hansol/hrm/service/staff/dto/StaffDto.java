package com.hansol.hrm.service.staff.dto;

import com.hansol.hrm.service.staff.domain.Staff;

import lombok.Builder;
import lombok.Getter;

@Getter
public class StaffDto {

	private Long id;
	private String name;
	private String phoneNumber;
	private String type;
	private Long taskId;
	private Long positionId;

	public StaffDto() {
	}

	@Builder
	public StaffDto(Long id, String name, String phoneNumber, String type, Long taskId, Long positionId) {
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.type = type;
		this.taskId = taskId;
		this.positionId = positionId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static StaffDto convertEntityToDto(Staff staffEntity) {
		return StaffDto.builder()
			.id(staffEntity.getId())
			.name(staffEntity.getName())
			.phoneNumber(staffEntity.getPhoneNumber())
			.type(staffEntity.getType())
			.taskId(staffEntity.getTaskId())
			.positionId(staffEntity.getPositionId())
			.build();
	}
}
