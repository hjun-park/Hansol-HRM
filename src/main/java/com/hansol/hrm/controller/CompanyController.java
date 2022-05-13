package com.hansol.hrm.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hansol.hrm.global.response.BaseResponse;
import com.hansol.hrm.service.company.CompanyService;
import com.hansol.hrm.service.company.dto.CompanyDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/company") // /swagger-ui/index.html
public class CompanyController {

	private final CompanyService companyService;

	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

	@Operation(summary = "회사 전체 조회")
	@ApiResponses({
		@ApiResponse(responseCode = "1000", description = "성공")
	})
	@GetMapping
	public BaseResponse<List<CompanyDto>> getCompanies() {
		return new BaseResponse<>(companyService.findCompanies());
	}

	@Operation(summary = "ID로 특정 회사 조회")
	@ApiResponses({
		@ApiResponse(responseCode = "1000", description = "성공"),
		@ApiResponse(responseCode = "3001", description = "조회할 정보 미존재")
	})
	@GetMapping("/{companyId}")
	public BaseResponse<CompanyDto> getCompanyById(@PathVariable Long companyId) {
		return new BaseResponse<>(companyService.findCompanyBy(companyId));
	}

	@Operation(summary = "회사 등록")
	@ApiResponses({
		@ApiResponse(responseCode = "1000", description = "성공"),
		@ApiResponse(responseCode = "2102", description = "중복된 값 등록불가"),
		@ApiResponse(responseCode = "4002", description = "DB 입력 실패")
	})
	@PostMapping
	public BaseResponse<Long> createCompany(@Valid @RequestBody CompanyDto companyDto) {
		return new BaseResponse<>(companyService.addCompany(companyDto));
	}

	@Operation(summary = "회사 수정")
	@ApiResponses({
		@ApiResponse(responseCode = "1000", description = "성공"),
		@ApiResponse(responseCode = "4003", description = "변경 대상 미존재")
	})
	@PatchMapping("/{companyId}")
	public BaseResponse<Long> modifyCompany(@PathVariable Long companyId, @Valid @RequestBody CompanyDto companyDto) {
		return new BaseResponse<>(companyService.editCompany(companyId, companyDto));
	}

	@Operation(summary = "회사 삭제")
	@ApiResponses({
		@ApiResponse(responseCode = "1000", description = "성공"),
		@ApiResponse(responseCode = "4004", description = "삭제 대상 미존재")
	})
	@DeleteMapping("/{companyId}")
	public BaseResponse<Long> removeCompany(@PathVariable Long companyId) {
		return new BaseResponse<>(companyService.deleteCompany(companyId));
	}
}
