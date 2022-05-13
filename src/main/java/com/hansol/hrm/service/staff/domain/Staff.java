package com.hansol.hrm.service.staff.domain;

import java.time.LocalDateTime;

import com.hansol.hrm.service.staff.dto.StaffDto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Staff {

	private Long id;
	private String name;
	private String phoneNumber;
	private String type;
	private Long taskId;
	private Long positionId;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public Staff() {
	}

	@Builder
	public Staff(Long id, String name, String phoneNumber, String type, Long taskId, Long positionId,
		LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.type = type;
		this.taskId = taskId;
		this.positionId = positionId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public static Staff convertDtoToEntity(StaffDto staffDto) {
		return Staff.builder()
			.name(staffDto.getName())
			.phoneNumber(staffDto.getPhoneNumber())
			.type(staffDto.getType())
			.taskId(staffDto.getTaskId())
			.positionId(staffDto.getPositionId())
			.build();
	}
}
