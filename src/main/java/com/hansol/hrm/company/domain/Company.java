package com.hansol.hrm.company.domain;

import com.hansol.hrm.company.dto.CompanyDto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Company {

	private Long id;
	private String name;

	public Company() {
	}

	@Builder
	public Company(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public static Company convertDtoToEntity(CompanyDto companyDto) {
		return Company.builder()
			.name(companyDto.getName())
			.build();
	}
}
