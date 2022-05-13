package com.hansol.hrm.service.company.dto;

import com.hansol.hrm.service.company.domain.Company;
import com.hansol.hrm.service.staff.domain.Staff;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CompanyDto {

	private Long id;
	private String name;

	public CompanyDto() {
	}

	@Builder
	public CompanyDto(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static CompanyDto convertEntityToDto(Company companyEntity) {
		return CompanyDto.builder()
			.id(companyEntity.getId())
			.name(companyEntity.getName())
			.build();
	}
}
