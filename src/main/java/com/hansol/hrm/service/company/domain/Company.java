package com.hansol.hrm.service.company.domain;

import java.time.LocalDateTime;

import com.hansol.hrm.service.company.dto.CompanyDto;
import com.hansol.hrm.service.staff.dto.StaffDto;

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
