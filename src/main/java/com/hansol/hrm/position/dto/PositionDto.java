package com.hansol.hrm.position.dto;

import com.hansol.hrm.position.domain.Position;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PositionDto {

	private Long id;
	private String name;

	public PositionDto() {
	}

	@Builder
	public PositionDto(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static PositionDto convertEntityToDto(Position positionEntity) {
		return PositionDto.builder()
			.id(positionEntity.getId())
			.name(positionEntity.getName())
			.build();
	}
}
