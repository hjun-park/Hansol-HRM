package com.hansol.hrm.company.dto;

import com.hansol.hrm.company.domain.Company;

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
