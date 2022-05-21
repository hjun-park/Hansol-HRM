package com.hansol.hrm.position.domain;

import com.hansol.hrm.position.dto.PositionDto;

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
