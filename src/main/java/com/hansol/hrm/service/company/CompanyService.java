package com.hansol.hrm.service.company;

import java.util.List;

import com.hansol.hrm.service.company.dto.CompanyDto;

public interface CompanyService {

	// 회사 전체 조회
	List<CompanyDto> findCompanies();

	// 회사 ID로 조회
	CompanyDto findCompanyBy(Long companyId);

	// 회사 등록
	Long addCompany(CompanyDto companyDto);

	// 회사 이름 수정
	Long editCompany(Long companyId, CompanyDto companyDto);

	// 회사 삭제
	Long deleteCompany(Long companyId);
}
