package com.hansol.hrm.service.position.domain;

import java.time.LocalDateTime;

import com.hansol.hrm.service.position.dto.PositionDto;
import com.hansol.hrm.service.staff.dto.StaffDto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Position {

	private Long id;
	private String name;

	public Position() {
	}

	@Builder
	public Position(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public static Position convertDtoToEntity(PositionDto positionDto) {
		return Position.builder()
			.name(positionDto.getName())
			.build();
	}
}
